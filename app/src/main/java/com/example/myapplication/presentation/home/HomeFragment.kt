package com.example.myapplication.presentation.home

import android.util.Log.d
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.remote.RetrofitClient
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.presentation.BaseFragment
import kotlinx.coroutines.flow.collectLatest
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
            viewModel.userList.collectLatest { pagingData ->
                d("HomeFragment", "Received PagingData: ${pagingData}")
                adapter.submitData(pagingData)
            }
        }
    }
}