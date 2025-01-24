package com.example.myapplication.homePage

import android.content.Context
import android.util.Log.d
import androidx.navigation.fragment.findNavController
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun start() {
        displayEmail()
        binding.logoutBtn.setOnClickListener {
            clearSession()
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
        findNavController().navigate(R.id.action_homeFragment_to_logInFragment)
    }

    //displays the email which user used to log in
    private fun displayEmail() {
        val sharedPref = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val email = sharedPref.getString("email", "No email found")
        binding.emailText.text = email
    }

}