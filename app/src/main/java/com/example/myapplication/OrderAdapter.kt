package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerViewBinding

class OrdersDiffUtil: DiffUtil.ItemCallback<CardClass>(){
    override fun areItemsTheSame(oldItem: CardClass, newItem: CardClass): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardClass, newItem: CardClass): Boolean {
        return oldItem == newItem
    }

}

class OrderAdapter(private val onReviewClick: (CardClass) -> Unit):
    ListAdapter<CardClass, OrderAdapter.OrderViewHolder>(OrdersDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            RecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    inner class OrderViewHolder(private val binding: RecyclerViewBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CardClass) {
            binding.productName.text = item.productName
            binding.price.text = item.price.toString()
            binding.quantity.text = item.quantity.toString()
            binding.cardStatus.text = item.orderStatus
            binding.cardButton.text = item.cardButton
            binding.cardImage.setImageResource(item.cardImage)

            binding.cardStatus.setOnClickListener {
                if (item.orderStatus == "Completed") {
                    onReviewClick(item)
                }
            }
        }
    }
}