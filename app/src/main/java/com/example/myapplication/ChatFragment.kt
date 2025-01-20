package com.example.myapplication

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentChatBinding

class ChatFragment : BaseFragment<FragmentChatBinding>(FragmentChatBinding::inflate) {
    private val viewModel: MessageViewModel by viewModels()
    override fun start() {
        val adapter = MessageAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.messages.observe(viewLifecycleOwner) {
            viewModel.jsonParsing()
            adapter.submitList(it)
        }

    }

}