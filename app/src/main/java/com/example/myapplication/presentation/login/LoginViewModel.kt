import android.content.Context
import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UserInfo
import com.example.myapplication.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class LoginViewModel: ViewModel() {

    //logs in users by sending info to server
    fun loginPost(context: Context, email: String, password: String, rememberMe: Boolean, onSuccess: () -> Unit, onError: (String) -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val responseBody = RetrofitClient.retrofit.postLogin(UserInfo(email, password))
                if (responseBody.isSuccessful && responseBody.body() != null) {
                    val token = responseBody.body()?.token ?: ""
                    saveData(context, email, token, rememberMe)
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

    //saves data
    private fun saveData(context: Context, email: String, token:String, rememberMe: Boolean){
        val sharedPref = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putString("email", email)
            putString("token", token)
            putBoolean("isLoggedIn", rememberMe)
            apply()
        }
    }

//    private val _loginStatus = MutableStateFlow<Resource<String>>(value = Resource.Default(""))
//    val loginStatus: StateFlow<Resource<String>> get() = _loginStatus.asStateFlow()
//
//    fun login(email:String, password: String, rememberMe: Boolean){
//        viewModelScope.launch {
//            _loginStatus.emit(Resource.Loading)
//
//            val credentials = getData()
//
//            val result = handleHttpRequest(apiCall = {RetrofitClient.retrofit.logIn(Request(credentials.email, credentials.password))})
//
//            when(result) {
//                is Resource.Success -> {
//                    if(rememberMe){
//                        DataStoreManager.saveValue(PreferenceKeys.EMAIL, email)
//                        DataStoreManager.saveValue(PreferenceKeys.PASSWORD, password)
//                    }
//                    _loginStatus.emit(Resource.Success("Login successful"))
//                }
//                is Resource.Error -> {
//                    _loginStatus.emit(Resource.Error(result.errorMessage))
//                }
//                else -> {
//
//                }
//            }
//
//        }
//    }
//
//    suspend fun getData(): Request {
//        val email = DataStoreManager.readValue(PreferenceKeys.EMAIL)?.first().toString()
//        val password = DataStoreManager.readValue(PreferenceKeys.PASSWORD)?.first().toString()
//        return Request(email, password)
//    }
//
//    suspend fun isRememberMeEnabled(): Boolean {
//        return DataStoreManager.readValue(PreferenceKeys.EMAIL)?.first().toString().isNotEmpty()
//    }
}