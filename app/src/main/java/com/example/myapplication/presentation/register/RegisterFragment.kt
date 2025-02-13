package com.example.myapplication.presentation.register

import RegisterViewModel
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRegisterBinding
import com.example.myapplication.presentation.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val userRegisterViewModel: RegisterViewModel by viewModels()

    override fun start() {
        viewLifecycleOwner.lifecycleScope.launch {
            userRegisterViewModel.registrationStatus.collectLatest { status ->
                if (status != null) {
                    Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.registerBtn.setOnClickListener {
            checkEmptyFields()
        }
    }

    //checks for empty fields and validates them
    private fun checkEmptyFields() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val repeatPassword = binding.repeatPassword.text.toString()

        if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            return Toast.makeText(
                requireContext(),
                "Please fill in all the fields.",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (email != "eve.holt@reqres.in") {
            return Toast.makeText(
                requireContext(),
                "You cannot register with this email.",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (repeatPassword != password) {
            Toast.makeText(requireContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show()
        } else {
            sendResultToLogIn(email, password)
            navigateToLogIn(email, password)
        }


        userRegisterViewModel.registerPost(
            email,
            password,
            onSuccess = { savedEmail, token ->
                Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT)
                    .show()
                sendResultToLogIn(savedEmail, password)
                navigateToLogIn(savedEmail, token)
            },
            onError = { errorMessage ->
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        )

    }

    //saves email and password and sends it to log in fragment
    private fun sendResultToLogIn(email: String, password: String) {
        val sharedPref =
            requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("registeredEmail", email)
        editor.putString("registeredPassword", password)
        editor.apply()

        val result = Bundle().apply {
            putString("email", email)
            putString("password", password)
        }
        setFragmentResult("registerResult", result)
    }

    //navigation to "Log in" page
    private fun navigateToLogIn(email: String, password: String) {
        val result = Bundle().apply {
            putString("email", email)
            putString("password", password)
        }
        setFragmentResult("registerResult", result)
        findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
    }
}



//    private val registerViewModel: RegisterViewModel by viewModels()
//
//    override fun start() {
//        listeners()
//        observeRegistration()
//    }
//
//    private fun validateEmail(email:String): Boolean{
//        val validEmail = "eve.holt@reqres.in"
//
//        return if(email != validEmail){
//            Toast.makeText(requireContext(), "Email is not valid", Toast.LENGTH_SHORT).show()
//            false
//        }else
//            true
//    }
//
//    private fun validatePassword(password:String, repeatedPassword:String): Boolean{
//        return when{
//            password.isEmpty() || repeatedPassword.isEmpty() -> {
//                Toast.makeText(requireContext(), "Passwords field is empty", Toast.LENGTH_SHORT).show()
//                false
//            }
//
//            password != repeatedPassword -> {
//                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
//                false
//            }
//
//            else -> true
//        }
//    }
//
//    private fun register(){
//        val email = binding.email.text.toString()
//        val password = binding.password.text.toString()
//        val repeatedPassword = binding.repeatPassword.text.toString()
//
//        if(validateEmail(email) && validatePassword(password, repeatedPassword))
//            registerViewModel.register(email, password)
//    }
//
//    private fun listeners(){
//        binding.registerBtn.setOnClickListener {
//            register()
//        }
//    }
//
//    private fun observeRegistration(){
//        viewLifecycleOwner.lifecycleScope.launch{
//            registerViewModel.registrationStatus.collect{ result ->
//                when (result) {
//                    is Resource.Loading -> {
//                        binding.loader.visibility = View.VISIBLE
//                    }
//                    is Resource.Success<String> -> {
//                        binding.loader.visibility = View.GONE
//                        val email = binding.email.text.toString()
//                        val password = binding.password.text.toString()
//
//                        viewLifecycleOwner.lifecycleScope.launch {
//                            DataStoreManager.saveValue(key = PreferenceKeys.EMAIL, email)
//                            DataStoreManager.saveValue(key = PreferenceKeys.PASSWORD, password)
//                            d("RegisterFragment", "Saved email: $email, password: $password")
//                        }
//
//                        findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
//                    }
//
//                    is Resource.Error -> {
//                        binding.loader.visibility = View.GONE
//                        Toast.makeText(requireContext(), result.errorMessage, Toast.LENGTH_SHORT).show()
//                    }
//
//                    is Resource.Default -> {
//
//                    }
//                }
//            }
//        }
//    }
//}

