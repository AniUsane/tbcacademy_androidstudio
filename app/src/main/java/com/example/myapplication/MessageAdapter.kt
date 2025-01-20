package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.RecyclerViewBinding

class CardDiffUtil : DiffUtil.ItemCallback<MessageInfo>() {
    override fun areItemsTheSame(oldItem: MessageInfo, newItem: MessageInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessageInfo, newItem: MessageInfo): Boolean {
        return oldItem == newItem
    }

}

class MessageAdapter: ListAdapter<MessageInfo, MessageAdapter.MessageViewHolder>(CardDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            RecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    inner class MessageViewHolder(private val binding: RecyclerViewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(message: MessageInfo){
            binding.username.text = message.owner
            binding.message.text = message.lastMessage
            loadImage(message.image, binding.profilePic)
            binding.unreadMessages.setText(message.unreadMessages.toString())


        }

        private fun loadImage(imageUrl: String, imageView: ImageView) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .circleCrop()
                .into(imageView)
        }
    }
}