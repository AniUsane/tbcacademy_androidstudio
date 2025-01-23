package com.example.myapplication.logInPage

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.myapplication.BaseFragment
import com.example.myapplication.databinding.FragmentLogInBinding


class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {
    private val userLogInViewModel: UserLogInViewModel by viewModels()

    override fun start() {
        listener()
    }

    private fun listener(){
        binding.loginBtn.setOnClickListener{
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                userLogInViewModel.loginPost(email, password)
            }
        }
    }
}