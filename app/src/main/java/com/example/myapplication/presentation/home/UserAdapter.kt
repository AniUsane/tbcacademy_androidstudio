package com.example.myapplication.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.remote.User
import com.example.myapplication.databinding.RecyclerViewBinding

class UserDiffUtil: DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}

class UserAdapter: PagingDataAdapter<User, UserAdapter.UserViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            RecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val model = getItem(position)
        if(model != null)
            holder.onBind(model)
    }

    class UserViewHolder(private val binding: RecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: User) {

            binding.username.text = "${model.first_name} ${model.last_name}"
            Glide.with(binding.root.context)
                .load(model.avatar)
                .into(binding.avatar)
        }
    }
}