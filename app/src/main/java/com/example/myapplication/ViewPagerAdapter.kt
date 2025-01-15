package com.example.myapplication

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CardLayoutBinding

class CardDiffUtil: DiffUtil.ItemCallback<CardInfo>(){
    override fun areItemsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem == newItem
    }

}

class ViewPagerAdapter(private val cardLongClick: (CardInfo) -> Unit) : ListAdapter<CardInfo, ViewPagerAdapter.ViewPagerViewHolder>(CardDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )

    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }


    inner class ViewPagerViewHolder(private val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(items: CardInfo){
            binding.cardNumber.text = items.cardNumber.toString()
            binding.holderName.text = items.holderName
            d("ViewPagerAdapter", "Binding card: ${items.cardNumber}")

            when (items.cardStatus) {
                CardType.VISA -> {
                    binding.root.setBackgroundResource(R.drawable.visa_bcgkrnd)
                }
                CardType.MASTERCARD -> {
                    binding.root.setBackgroundResource(R.drawable.mastercard_bckgrnd)

                    val layoutParams = binding.holderName.layoutParams as ViewGroup.MarginLayoutParams


                }
            }

            binding.cardLayout.setOnLongClickListener {
                cardLongClick(items)
                true
            }



        }


    }

    fun removeCard(card: CardInfo) {
        val currentList = currentList.toMutableList()
        currentList.remove(card)
        submitList(currentList)
    }

}