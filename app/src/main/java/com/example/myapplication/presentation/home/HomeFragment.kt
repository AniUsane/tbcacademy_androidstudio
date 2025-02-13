package com.example.myapplication.presentation.home

import android.util.Log.d
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.remote.RetrofitClient
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var viewModel: HomeViewModel
    private val adapter = UserAdapter()
    @Inject
    lateinit var repository: UserRepository
    override fun start() {
        viewModel = HomeViewModel(repository)

        binding.userRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.userRecycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userList.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

    }
}