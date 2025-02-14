package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.CardBinding

class ViewPagerAdapter(private val context: Context,
    private var cardList: List<CardData>):RecyclerView.Adapter<ViewPagerAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            CardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    inner class CardViewHolder(private val binding: CardBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(card: CardData) {
            binding.likes.text = card.reaction_count.toString()
            binding.price.text = card.price
            binding.location.text = card.location
            binding.imageTitle.text = card.title

            binding.rating.rating = card.rate

            Glide.with(context).load(card.cover).into(binding.mainImage)
        }
    }

    fun updateData(newCards: List<CardData>) {
        this.cardList = newCards
        notifyDataSetChanged()
    }
}