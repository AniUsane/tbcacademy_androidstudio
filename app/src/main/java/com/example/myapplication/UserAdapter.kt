package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.User
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.RecyclerViewBinding

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id // comparison by id is usually enough
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem // compares the full object
    }
}


class UserAdapter: ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

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
        val user = getItem(position) // Ensure this doesn't return null
        if (user != null) {
            holder.onBind(user)
        }
    }

    inner class UserViewHolder(private val binding: RecyclerViewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(user: User){

            binding.name.text = "${user.firstName} ${user.lastName}"
            binding.activeStatus.text =checkStatus(user.activationStatus)
            loadImage(user.avatar.toString(), binding.profileImg)



        }
        private fun loadImage(imageUrl: String, imageView: ImageView) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .error(R.drawable.profile_pic)
                .into(imageView)

        }

        private fun checkStatus(status: Int):String {
            return when(status){
                0 -> "User is not active"
                1 -> "Online"
                2 -> "User was active few minutes ago"
                in 2..23 -> "User was active hours ago"
                else -> "User was active long time ago"
            }
        }
    }
}