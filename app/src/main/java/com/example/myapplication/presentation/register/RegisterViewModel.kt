package com.example.myapplication.registerPage

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UserInfo
import com.example.myapplication.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RegisterViewModel:ViewModel() {

    private val _registrationStatus = MutableStateFlow<String?>(null)
    val registrationStatus: StateFlow<String?> get() = _registrationStatus.asStateFlow()

    // Sends request to server
    fun registerPost(
        email: String,
        password: String,
        onSuccess: (String, String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseBody = try {
                RetrofitClient.retrofit.postRegister(UserInfo(email, password))
            } catch (e: IOException) {
                d("RegisterViewModel", "IOException, you might not have an internet connection")
                _registrationStatus.value = "No internet connection."
                onError("No internet connection.")
                return@launch // Ensures the function stops executing
            } catch (e: HttpException) {
                d("RegisterViewModel", "HttpException, unexpected response")
                _registrationStatus.value = "Registration failed."
                onError("Registration failed.")
                return@launch // Ensures the function stops executing
            } catch (e: Exception) {
                d("RegisterViewModel", "Unexpected error occurred.")
                _registrationStatus.value = "Unexpected error occurred."
                onError("Unexpected error occurred.")
                return@launch // Ensures the function stops executing
            }

            if (!responseBody.isSuccessful) {
                d("RegisterPost", "Registration failed: ${responseBody.errorBody()?.string()}")
                _registrationStatus.value = "Registration failed."
                onError("Registration failed.")
                return@launch // Ensures the function stops executing
            }

            val response = responseBody.body()
            if (response == null || response.token.isNullOrEmpty()) {
                d("RegisterPost", "Registration failed: response body is null or missing token")
                _registrationStatus.value = "Registration failed."
                onError("Registration failed.")
                return@launch // Ensures the function stops executing
            }

            // Successful registration
            _registrationStatus.value = "Registration successful!"
            onSuccess(email, password)
        }
    }
}

//suspend fun <T> handleHttpRequest(apiCall: suspend() -> Response<T>): Resource<T> {
//    val response = apiCall.invoke()
//    return try{
//        if(response.isSuccessful) {
//            response.body()?.let{
//                Resource.Success(data = it)
//            } ?: Resource.Error(errorMessage = "Error")
//        }else{
//            Resource.Error(errorMessage = response.message())
//        }
//    }catch (throwable: Throwable) {
//        when(throwable) {
//            is HttpException -> {
//                Resource.Error(errorMessage = throwable.message?: "Registration failed.")
//            }
//            is IOException -> {
//                Resource.Error(errorMessage = throwable.message?: "No internet connection.")
//            }
//            else -> {
//                Resource.Error(errorMessage = throwable.message?: "Unexpected error occurred.")
//            }
//        }
//    }
//}