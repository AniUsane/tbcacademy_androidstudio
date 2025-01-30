package com.example.myapplication

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val userViewModel: UserViewModel by viewModels {
        UserInfoFactory(requireContext())
    }

    override fun start() {
        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.userInfo.collect { userInfo ->
                if(userInfo != null) {
                    binding.readInfo.text = "First name: ${userInfo?.firstName}\n" +
                            "Last name: ${userInfo?.lastName}\n" +
                            "Email: ${userInfo?.email}"
                }else{
                    Toast.makeText(requireContext(), "Could not read info.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        listeners()
    }

    private fun listeners(){
        binding.saveBtn.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val email = binding.email.text.toString()

            viewLifecycleOwner.lifecycleScope.launch {
                userViewModel.saveUser(firstName, lastName, email)
            }

            Toast.makeText(requireContext(), "User info is saved.", Toast.LENGTH_SHORT).show()
        }

        binding.readBtn.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                userViewModel.printUserInfo()
            }
        }
    }
}