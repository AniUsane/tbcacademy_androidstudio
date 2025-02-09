package com.example.myapplication.presentation.profile

import android.content.Context
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.presentation.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun start() {
        displayEmail()
        binding.logoutBtn.setOnClickListener {
            clearSession()
        }

        binding.homeBtn.setOnClickListener {
            navigateToHome()
        }
    }

    //clears session
    private fun clearSession(){
        val sharedPref = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()
        navigateToLogIn()
    }

    //navigation to log in page
    private fun navigateToLogIn(){
        findNavController().navigate(R.id.action_profileFragment_to_logInFragment)
    }

    //navigation to home page
    private fun navigateToHome(){
        findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
    }


    //displays the email which user used to log in
    private fun displayEmail() {
        val sharedPref = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val email = sharedPref.getString("email", "No email found")
        binding.emailText.text = email
    }

}