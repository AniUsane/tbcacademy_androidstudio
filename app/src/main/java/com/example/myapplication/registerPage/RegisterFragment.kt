package com.example.myapplication.registerPage

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.logInPage.UserLogInViewModel
import com.example.myapplication.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val userRegisterViewModel: UserRegisterViewModel by viewModels()

    override fun start() {
        userRegisterViewModel.registrationStatus.observe(viewLifecycleOwner) { status ->
            Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
        }
        binding.registerBtn.setOnClickListener {
            checkEmptyFields()


        }



    }

    private fun checkEmptyFields(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val username = binding.username.text.toString()
        val repeatPassword = binding.repeatPassword.text.toString()

        if(email.isEmpty() || password.isEmpty() || username.isEmpty() || repeatPassword.isEmpty()){
            return Toast.makeText(requireContext(), "Please fill in all the fields.", Toast.LENGTH_SHORT).show()
        }

        if(email != "eve.holt@reqres.in"){
            return Toast.makeText(requireContext(), "You cannot register with this email.", Toast.LENGTH_SHORT).show()
        }

        if (repeatPassword != password) {
            Toast.makeText(requireContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show()
        }else{
            navigateToLogIn(email, password)
        }


        userRegisterViewModel.registerPost(
            email,
            password,
            onSuccess = { savedEmail, token ->
                Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show()
                sendResultToLogIn(savedEmail, password)
                navigateToLogIn(savedEmail, token)
            },
            onError = { errorMessage ->
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        )

    }

    private fun sendResultToLogIn(email: String, password: String) {
        val result = Bundle().apply {
            putString("email", email)
            putString("password", password)
        }
        setFragmentResult("registerResult", result)
    }

    private fun navigateToLogIn(email: String, password: String) {
        val result = Bundle().apply {
            putString("email", email)
            putString("password", password)
        }
        setFragmentResult("registerResult", result)
        findNavController().navigate(R.id.action_registerFragment_to_logInFragment)
    }


}