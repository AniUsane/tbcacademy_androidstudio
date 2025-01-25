package com.example.myapplication.registerPage

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.RetrofitClient
import com.example.myapplication.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UserRegisterViewModel:ViewModel() {

    private val _registrationStatus = MutableLiveData<String>()
    val registrationStatus: LiveData<String> get() = _registrationStatus

    //sends request to server
    fun registerPost(email:String, password:String, onSuccess: (String, String) -> Unit,
                     onError: (String) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = try {
                RetrofitClient.retrofit.postRegister(UserInfo(email, password))
            } catch(e: IOException){
                d("LogInFragment", "IOException, you might not have internet connection")
                _registrationStatus.postValue("No internet connection.")
                return@launch
            } catch (e: HttpException){
                d("LogInFragment", "HttpException, unexpected response")
                _registrationStatus.postValue("Registration failed.")
                return@launch
            } catch (e: Exception) {
                d("LogInFragment", "Unexpected error occurred.")
                _registrationStatus.postValue("Unexpected error occurred.")
                return@launch
            }

            if(responseBody.isSuccessful && responseBody.body() != null){
                val token = responseBody.body()!!.token
                _registrationStatus.postValue("Registration successful!")
            }else{
                d("RegisterPost", "Registration failed: ${responseBody.errorBody()}")
                _registrationStatus.postValue("Registration failed.")
            }
        }
    }
}