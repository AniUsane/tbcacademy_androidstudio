package com.example.myapplication

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentLockScreenBinding

class LockScreenFragment : BaseFragment<FragmentLockScreenBinding>(FragmentLockScreenBinding::inflate) {
    private val viewModel: NumpadViewModel by viewModels()

    override fun start() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = NumpadAdapter(viewModel.numpadButtons)
    }

}