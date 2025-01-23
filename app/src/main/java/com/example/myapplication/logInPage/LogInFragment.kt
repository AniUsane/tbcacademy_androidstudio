package com.example.myapplication.logInPage

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLogInBinding


class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {
    private val userLogInViewModel: UserLogInViewModel by viewModels()

    override fun start() {
        listener()
        listenForRegisterResult()
        checkSession()
    }

    private fun listener(){
        binding.loginBtn.setOnClickListener{
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()
            val rememberMe = binding.rememberMe.isChecked

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

            userLogInViewModel.loginPost(
                email = email,
                password = password,
                rememberMe = rememberMe,

                onSuccess = {
                    findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
                },
                onError = { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            )

        }
    }

    private fun listenForRegisterResult() {
        setFragmentResultListener("registerResult") { _, bundle ->
            val email = bundle.getString("email")
            val password = bundle.getString("password")

            binding.username.setText(email)
            binding.password.setText(password)
        }
    }

    private fun checkSession() {
        val sharedPref = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
        }
    }
}