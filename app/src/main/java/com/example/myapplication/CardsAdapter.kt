package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CardLayoutBinding
import com.example.myapplication.databinding.FilterLayoutBinding
import java.text.SimpleDateFormat
import java.util.Locale

class CardsDiffUtil: DiffUtil.ItemCallback<CardsData>(){
    override fun areItemsTheSame(oldItem: CardsData, newItem: CardsData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardsData, newItem: CardsData): Boolean {
        return oldItem == newItem
    }

}


class CardsAdapter(val cardsList: List<CardsData>,
    val clickedDetailsButton: (CardsData) -> Unit):
    ListAdapter<CardsData, CardsAdapter.CardsViewHolder>(CardsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        return CardsViewHolder(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class CardsViewHolder(val binding: CardLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(cardsModel: CardsData){
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            binding.date.text = dateFormat.format(cardsModel.date)
            binding.orderNumber.text = cardsModel.orderNumber
            binding.trackingNumber.text = cardsModel.trackingNumber
            binding.quantityCount.text = "${cardsModel.quantity}"
            binding.total.text = cardsModel.total
            binding.detailsBtn.setOnClickListener {
                clickedDetailsButton(cardsModel)
            }

            binding.orderStatus.text = cardsModel.orderStatus.name
        }

    }
}