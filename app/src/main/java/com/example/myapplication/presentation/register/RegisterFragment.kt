package com.example.myapplication.presentation.register

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.remote.Resource
import com.example.myapplication.data.remote.datastore.DataStoreManager
import com.example.myapplication.data.remote.datastore.PreferenceKeys
import com.example.myapplication.databinding.FragmentRegisterBinding
import com.example.myapplication.presentation.BaseFragment
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun start() {
        listeners()
        observeRegistration()
    }

    private fun validateEmail(email:String): Boolean{
        val validEmail = "eve.holt@reqres.in"
        return if(email != validEmail){
            Toast.makeText(requireContext(), "Email is not valid", Toast.LENGTH_SHORT).show()
            false
        }else
            true
    }

    private fun validatePassword(password:String, repeatedPassword:String): Boolean{
        return when{
            password.isEmpty() || repeatedPassword.isEmpty() -> {
                Toast.makeText(requireContext(), "Passwords field is empty", Toast.LENGTH_SHORT).show()
                false
            }
            password != repeatedPassword -> {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    private fun register(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val repeatedPassword = binding.repeatPassword.text.toString()

        if(validateEmail(email) && validatePassword(password, repeatedPassword))
            registerViewModel.register(email, password)
    }

    private fun listeners(){
        binding.registerBtn.setOnClickListener {
            register()
        }
    }

    private fun observeRegistration(){
        viewLifecycleOwner.lifecycleScope.launch{
            registerViewModel.registrationStatus.collect{ result ->
                when (result) {
                    is Resource.Loading -> {
                        binding.loader.visibility = View.VISIBLE
                    }
                    is Resource.Success<String> -> {
                        binding.loader.visibility = View.GONE
                        val email = binding.email.text.toString()
                        val password = binding.password.text.toString()

                        viewLifecycleOwner.lifecycleScope.launch {
                            DataStoreManager.saveValue(key = PreferenceKeys.EMAIL, email)
                            DataStoreManager.saveValue(key = PreferenceKeys.PASSWORD, password)
                            d("RegisterFragment", "Saved email: $email, password: $password")
                        }


                        findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
                    }

                    is Resource.Error -> {
                        binding.loader.visibility = View.GONE
                        Toast.makeText(requireContext(), result.errorMessage, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Default -> {

                    }
                }
            }
        }
    }
}