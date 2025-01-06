package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentChatBinding
import java.util.Date
import java.util.UUID


class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private var messageList = mutableListOf(
        MessagesData(id = UUID.randomUUID(), message = "Hello! Can I help you?", time = Date(), rightAligned = false),
        MessagesData(id = UUID.randomUUID(), message = "Hi! I have a question about my order", time = Date(), rightAligned = true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MessagesAdapter()
        binding.chatRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.chatRecycler.adapter = adapter
        adapter.submitList(messageList)
        sendMessages(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sendMessages(adapter: MessagesAdapter) {
        binding.sendBtn.setOnClickListener {
            if(binding.typeMessage.text?.trim()?.isNotEmpty() == true) {
                val rightAligned: Boolean = messageList.size % 2 != 0
                messageList.add(
                    MessagesData(UUID.randomUUID(), binding.typeMessage.text.toString(), Date(), rightAligned)
                )
                adapter.submitList(messageList)
            }
            binding.chatRecycler.scrollToPosition(messageList.size - 1)
            binding.typeMessage.text?.clear()
        }
    }


}