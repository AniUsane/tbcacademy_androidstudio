package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerViewBinding

class ItemsDiffUtil: DiffUtil.ItemCallback<Items>(){
    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem == newItem
    }

}

class ItemsAdapter(private val itemClickListener: (Items) -> Unit): ListAdapter<Items, ItemsAdapter.ItemsViewHolder>(ItemsDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            RecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    inner class ItemsViewHolder(private val binding: RecyclerViewBinding):
        RecyclerView.ViewHolder(binding.root){
            fun onBind(item: Items){
                binding.imageBtn.setImageResource(item.image)

                binding.imageBtn.setOnClickListener{
                    itemClickListener(item)
                }
            }
        }

}