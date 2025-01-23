package com.example.myapplication.logInPage

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

class UserLogInViewModel: ViewModel() {

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
            } catch (e: Exception) {
                d("LogInFragment", "Unexpected error occurred.")
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