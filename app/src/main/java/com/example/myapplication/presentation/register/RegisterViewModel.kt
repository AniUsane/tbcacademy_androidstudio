import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.Request
import com.example.myapplication.data.remote.Resource
import com.example.myapplication.data.remote.RetrofitClient
import com.example.myapplication.data.remote.HandleHttpRequests.handleHttpRequest
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

    //sends request to server
    fun registerPost(email: String, password: String, onSuccess: (String, String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            _registrationStatus.emit(Resource.Loading.toString())

            val responseBody = try {
                RetrofitClient.retrofit.postRegister(Request(email, password))
            } catch (e: IOException) {
                d("LogInFragment", "IOException, you might not have internet connection")
                _registrationStatus.value = "No internet connection."
                return@launch
            } catch (e: HttpException) {
                d("LogInFragment", "HttpException, unexpected response")
                _registrationStatus.value = "Registration failed."
                return@launch
            } catch (e: Exception) {
                d("LogInFragment", "Unexpected error occurred.")
                _registrationStatus.value = "Unexpected error occurred."
                return@launch
            }

            if (responseBody.isSuccessful && responseBody.body() != null) {
                val token = responseBody.body()!!.token
                _registrationStatus.value = "Registration successful!"
            } else {
                d("RegisterPost", "Registration failed: ${responseBody.errorBody()}")
                _registrationStatus.value = "Registration failed."
            }
        }
    }
}

//    private val _registrationStatus = MutableStateFlow<Resource<String>>(Resource.Default(""))
//    val registrationStatus: StateFlow<Resource<String>> get() = _registrationStatus.asStateFlow()
//
//
//    fun register(email: String, password: String) {
//        viewModelScope.launch {
//
//            _registrationStatus.emit(Resource.Loading)
//
//            val result = HandleHttpRequests.handleHttpRequest(apiCall = {
//                RetrofitClient.retrofit.register(
//                    Request(
//                        email,
//                        password
//                    )
//                )
//            })
//
//            when (result) {
//
//                is Resource.Success -> {
//                    _registrationStatus.emit(Resource.Success("Registration successful"))
//                }
//
//                is Resource.Error -> {
//                    _registrationStatus.emit(Resource.Error(result.errorMessage))
//                }
//
//                else -> {
//
//                }
//            }
//        }
//    }




