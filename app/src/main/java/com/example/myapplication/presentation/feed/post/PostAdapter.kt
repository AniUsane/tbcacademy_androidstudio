package com.example.myapplication.presentation.feed.post

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.myapplication.R
import com.example.myapplication.databinding.PostRecyclerBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class PostDiffUtil: DiffUtil.ItemCallback<Posts>(){
    override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
        return oldItem == newItem
    }

}

class PostAdapter @Inject constructor(
    private val glide: RequestManager
): ListAdapter<Posts, PostAdapter.PostViewHolder>(PostDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        d("PostAdapter", "Binding item: ${item.title}, Position: $position")

        holder.onBind(item)
    }

    inner class PostViewHolder(private val binding: PostRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Posts) {
            binding.userName.text = "${item.owner.firstName} ${item.owner.lastName}"
            binding.title.text = item.title
            binding.date.text = formatTime(item.owner.postDate)
            glide.load(item.owner.profile)
                .error(R.drawable.default_pfp)
                .placeholder(R.drawable.placeholder_image)
                .into(binding.userProfileImg)

            binding.comment.text = "${item.comments} Comments"
            binding.likes.text = "${item.likes.toString()} Likes"
            binding.shares.text = item.shareContent

            val imageList = item.images ?: emptyList()

            if (imageList.isNotEmpty()) {
                if (imageList.size > 0) {
                    binding.postImg1.visibility = View.VISIBLE
                    glide.load(imageList[0])
                        .placeholder(R.drawable.placeholder_image)
                        .into(binding.postImg1)
                }
                if (imageList.size > 1) {
                    binding.postImg2.visibility = View.VISIBLE
                    glide.load(imageList[1])
                        .placeholder(R.drawable.placeholder_image)
                        .into(binding.postImg2)
                }
                if (imageList.size > 2) {
                    binding.postImg3.visibility = View.VISIBLE
                    glide.load(imageList[2])
                        .placeholder(R.drawable.placeholder_image)
                        .into(binding.postImg3)
                }
            }
        }


        fun formatTime(time: Long): String {
            val date = Date(time * 1000)
            val formatted = SimpleDateFormat("dd MMMM 'at' h:mm a", Locale.getDefault())
            return formatted.format(date)
        }

    }
}