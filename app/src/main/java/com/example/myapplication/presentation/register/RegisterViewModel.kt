package com.example.myapplication.presentation.register

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.Request
import com.example.myapplication.data.remote.Resource
import com.example.myapplication.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class RegisterViewModel: ViewModel() {
    private val _registrationStatus = MutableStateFlow<Resource<String>>(Resource.Default(""))
    val registrationStatus: StateFlow<Resource<String>> get() = _registrationStatus.asStateFlow()

    fun register(email:String, password:String){
        viewModelScope.launch {
            _registrationStatus.emit(Resource.Loading)

            val result = handleHttpRequest(apiCall = {RetrofitClient.retrofit.register(Request(email, password))})

            when(result) {
                is Resource.Success -> {
                    _registrationStatus.emit(Resource.Success("Registration successful"))
                }
                is Resource.Error -> {
                    _registrationStatus.emit(Resource.Error(result.errorMessage))
                }
                else -> {
                }
            }
        }
    }
}

suspend fun <T> handleHttpRequest(apiCall: suspend() -> Response<T>): Resource<T> {
    val response = apiCall.invoke()
    return try{
        if(response.isSuccessful) {
            response.body()?.let{
                Resource.Success(data = it)
            } ?: Resource.Error(errorMessage = "Error")
        }else{
            Resource.Error(errorMessage = response.message())
        }
    }catch (throwable: Throwable) {
        when(throwable) {
            is HttpException -> {
                Resource.Error(errorMessage = throwable.message?: "Registration failed.")
            }
            is IOException -> {
                Resource.Error(errorMessage = throwable.message?: "No internet connection.")
            }
            else -> {
                Resource.Error(errorMessage = throwable.message?: "Unexpected error occurred.")
            }
        }
    }
}