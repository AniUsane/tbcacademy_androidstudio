package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerViewBinding

class NumpadAdapter(private val buttons: List<NumpadClass>): RecyclerView.Adapter<NumpadAdapter.NumpadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumpadViewHolder {
        return NumpadViewHolder(
            RecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return buttons.size
    }

    override fun onBindViewHolder(holder: NumpadViewHolder, position: Int) {
        holder.onBind(buttons[position])
    }

    inner class NumpadViewHolder(private val binding: RecyclerViewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(num: NumpadClass){
            binding.keyBtn.apply {
                text = num.toString() ?: ""
            }
        }
    }

}