package com.example.myapplication.homePage

import android.util.Log.d
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.BaseFragment
import com.example.myapplication.RetrofitClient
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var viewModel: HomeViewModel
    private val adapter = UserAdapter()
    override fun start() {
        val repository = UserRepository(RetrofitClient.retrofit)
        viewModel = HomeViewModel(repository)

        binding.userRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.userRecycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect { userList ->
                d("HomeFragment", "User list: $userList")
                adapter.submitList(userList)
            }
        }

        viewModel.fetchUsers()



    }

}