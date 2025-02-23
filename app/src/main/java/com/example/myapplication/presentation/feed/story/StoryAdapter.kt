package com.example.myapplication.presentation.feed.story

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.myapplication.R
import com.example.myapplication.databinding.StoryRecyclerBinding
import javax.inject.Inject

class StoryDiffUtil: DiffUtil.ItemCallback<Story>(){
    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }

}

class StoryAdapter @Inject constructor(
    private val glide: RequestManager
): ListAdapter<Story, StoryAdapter.StoryViewHolder>(StoryDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(
            StoryRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val item = getItem(position)
        d("StoryAdapter", "Binding item: ${item.title}, Position: $position")

        holder.onBind(item)
    }

    inner class StoryViewHolder(private val binding: StoryRecyclerBinding):
            RecyclerView.ViewHolder(binding.root){
                fun onBind(item: Story){
                    binding.storyText.text = item.title
                    glide.load(item.cover)
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .into(binding.storyImage)

                }
            }

}