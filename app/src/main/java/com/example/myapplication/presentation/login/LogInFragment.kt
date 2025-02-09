package com.example.myapplication.presentation.login

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.remote.Resource
import com.example.myapplication.data.remote.datastore.DataStoreManager
import com.example.myapplication.data.remote.datastore.PreferenceKeys
import com.example.myapplication.databinding.FragmentLogInBinding
import com.example.myapplication.presentation.BaseFragment
import com.example.myapplication.presentation.register.RegisterViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun start() {
        listeners()
        checkRememberMe()
    }

    private fun autoFillFields() {
        viewLifecycleOwner.lifecycleScope.launch {
            val credentials = loginViewModel.getData()
            binding.email.setText(credentials.email)
            binding.password.setText(credentials.password)
        }
    }

    private fun listeners(){
        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registerFragment)
        }
        binding.logInBtn.setOnClickListener{
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val rememberMe = binding.checkBox.isChecked

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginViewModel.login(email, password, rememberMe)

            observeLogin()
        }
    }

    private fun observeLogin(){
            viewLifecycleOwner.lifecycleScope.launch{
                loginViewModel.loginStatus.collectLatest{ result ->
                    when (result) {
                        is Resource.Loading -> {
                            binding.loader.visibility = View.VISIBLE
                        }
                        is Resource.Success<String> -> {
                            binding.loader.visibility = View.GONE
                            findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
                        }

                        is Resource.Error -> {
                            binding.loader.visibility = View.GONE
                            Toast.makeText(requireContext(), result.errorMessage, Toast.LENGTH_SHORT).show()
                        }

                        is Resource.Default -> {

                        }
                    }
                }
            }
    }

    private fun checkRememberMe() {
        viewLifecycleOwner.lifecycleScope.launch {
            val credentials = loginViewModel.getData()
            if (credentials.email.isNotEmpty() && credentials.password.isNotEmpty()) {
                // Auto-fill if credentials are available
                binding.email.setText(credentials.email)
                binding.password.setText(credentials.password)
                binding.checkBox.isChecked = true
            }
        }
    }

}