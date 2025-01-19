package com.example.myapplication.welcomePage

import androidx.navigation.fragment.findNavController
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWelcomeBinding

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override fun start() {
        listeners()
    }

    private fun listeners(){
        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_logInFragment)
        }
        binding.registerBtn.setOnClickListener{
            findNavController().navigate(R.id.action_welcomeFragment_to_registerFragment)
        }
    }
}