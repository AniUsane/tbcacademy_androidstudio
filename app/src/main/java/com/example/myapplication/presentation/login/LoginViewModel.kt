package com.example.myapplication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.Request
import com.example.myapplication.data.remote.Resource
import com.example.myapplication.data.remote.RetrofitClient
import com.example.myapplication.data.remote.datastore.DataStoreManager
import com.example.myapplication.data.remote.datastore.PreferenceKeys
import com.example.myapplication.presentation.register.handleHttpRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _loginStatus = MutableStateFlow<Resource<String>>(value = Resource.Default(""))
    val loginStatus: StateFlow<Resource<String>> get() = _loginStatus.asStateFlow()

    fun login(email:String, password: String, rememberMe: Boolean){
        viewModelScope.launch {
            _loginStatus.emit(Resource.Loading)

            val credentials = getData()

            val result = handleHttpRequest(apiCall = {RetrofitClient.retrofit.logIn(Request(credentials.email, credentials.password))})

            when(result) {
                is Resource.Success -> {
                    if(rememberMe){
                        DataStoreManager.saveValue(PreferenceKeys.EMAIL, email)
                        DataStoreManager.saveValue(PreferenceKeys.PASSWORD, password)
                    }
                    _loginStatus.emit(Resource.Success("Login successful"))
                }
                is Resource.Error -> {
                    _loginStatus.emit(Resource.Error(result.errorMessage))
                }
                else -> {

                }
            }

        }
    }

    suspend fun getData(): Request {
        val email = DataStoreManager.readValue(PreferenceKeys.EMAIL)?.first().toString()
        val password = DataStoreManager.readValue(PreferenceKeys.PASSWORD)?.first().toString()
        return Request(email, password)
    }

    suspend fun isRememberMeEnabled(): Boolean {
        return DataStoreManager.readValue(PreferenceKeys.EMAIL)?.first().toString().isNotEmpty()
    }

}