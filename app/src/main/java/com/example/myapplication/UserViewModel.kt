package com.example.myapplication

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UserViewModel: ViewModel() {

    private var registeredEmail: String? = null
    private var registeredPassword: String? = null

    private val _registrationStatus = MutableLiveData<String>()
    val registrationStatus: LiveData<String> get() = _registrationStatus

    fun registerPost(email:String, password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = try {
                RetrofitClient.retrofit.postRegister(UserInfo(email, password))
            } catch(e: IOException){
                d("LogInFragment", "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException){
                d("LogInFragment", "HttpException, unexpected response")
                return@launch
            }

            if(responseBody.isSuccessful && responseBody.body() != null){
                registeredEmail = email
                registeredPassword = password
                d("UserViewModel", "Email saved: $registeredEmail")
                d("UserViewModel", "Password saved: $registeredPassword")

            }else{
                d("RegisterPost", "Registration failed: ${responseBody.errorBody()}")
            }
        }
    }


    fun loginPost(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            val responseBody = try {
                RetrofitClient.retrofit.postLogin(UserInfo(email, password))
            } catch(e: IOException){
                d("LogInFragment", "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException){
                d("LogInFragment", "HttpException, unexpected response")
                return@launch
            }

            if(responseBody.isSuccessful && responseBody.body() != null){
                d("LoginPost", "Login successful: $responseBody")
            }else{
                d("LoginPost", "Login failed: ${responseBody.errorBody()}")
            }
        }
    }
}