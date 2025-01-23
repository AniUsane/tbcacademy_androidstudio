package com.example.myapplication.homePage

import android.content.Context
import androidx.navigation.fragment.findNavController
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun start() {
        binding.logoutBtn.setOnClickListener {
            clearSession()
            navigateToLogIn()
            displayEmail()
        }
    }

    private fun clearSession(){
        val sharedPref = requireActivity().getSharedPreferences("User Preferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    private fun navigateToLogIn(){
        findNavController().navigate(R.id.action_homeFragment_to_logInFragment)
    }

    private fun displayEmail() {
        val sharedPref = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val email = sharedPref.getString("email", "No email found")
        binding.emailText.text = email
    }

}