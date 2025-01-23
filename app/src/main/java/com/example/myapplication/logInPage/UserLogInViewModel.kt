package com.example.myapplication.logInPage

import android.content.Context
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


class UserLogInViewModel(private val context:Context): ViewModel() {

    fun loginPost(email: String, password: String, rememberMe: Boolean, onSuccess: () -> Unit, onError: (String) -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val responseBody = RetrofitClient.retrofit.postLogin(UserInfo(email, password))
                if (responseBody.isSuccessful && responseBody.body() != null) {
                    val token = responseBody.body()?.token ?: ""
                    saveData(email, token, rememberMe)
                    onSuccess()
                }else {
                    onError("Login failed. Please check your credentials.")
                }
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

        }
    }

    private fun saveData(email: String, token:String, rememberMe: Boolean){
        val sharedPref = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putString("email", email)
            putString("token", token)
            putBoolean("isLoggedIn", rememberMe)
            apply()
        }
    }
}