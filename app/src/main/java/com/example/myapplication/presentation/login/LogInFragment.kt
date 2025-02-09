package com.example.myapplication.presentation.login

import LoginViewModel
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLogInBinding
import com.example.myapplication.presentation.BaseFragment


class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {
    private val userLogInViewModel: LoginViewModel by viewModels()

    override fun start() {
        listener()
        listenForRegisterResult()
        checkSession()
    }

    //logic for "log in" button
    private fun listener(){
        binding.logInBtn.setOnClickListener{
            val password = binding.password.text.toString()
            val rememberMe = binding.checkBox.isChecked
            val email = binding.email.text.toString()

            val sharedPref = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
            val registeredPassword = sharedPref.getString("registeredPassword", null)

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password != registeredPassword){
                Toast.makeText(requireContext(), "Incorrect password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userLogInViewModel.loginPost(
                context = requireContext(),
                email = email,
                password = password,
                rememberMe = rememberMe,

                onSuccess = {
                    findNavController().navigate(R.id.action_logInFragment_to_profileFragment)
                },
                onError = { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            )

        }

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }
    }

    //gets data from registration and writes data in log in fields
    private fun listenForRegisterResult() {
        setFragmentResultListener("registerResult") { _, bundle ->
            val email = bundle.getString("email")
            val password = bundle.getString("password")

            binding.email.setText(email)
            binding.password.setText(password)
        }
    }

    private fun checkSession() {
        val sharedPref = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            findNavController().navigate(R.id.action_logInFragment_to_profileFragment)
        }
    }
}