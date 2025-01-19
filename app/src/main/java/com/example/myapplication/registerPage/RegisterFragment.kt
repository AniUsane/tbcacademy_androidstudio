package com.example.myapplication.registerPage

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.myapplication.BaseFragment
import com.example.myapplication.UserViewModel
import com.example.myapplication.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val userViewModel: UserViewModel by viewModels()

    override fun start() {
        userViewModel.registrationStatus.observe(viewLifecycleOwner) { status ->
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

        if(email.isEmpty() || password.isEmpty() || username.isEmpty()){
            return Toast.makeText(requireContext(), "Please fill in all the fields.", Toast.LENGTH_SHORT).show()
        }

        if(email != "eve.holt@reqres.in"){
            return Toast.makeText(requireContext(), "You cannot register with this email.", Toast.LENGTH_SHORT).show()
        }

        userViewModel.registerPost(email,password)

    }
}