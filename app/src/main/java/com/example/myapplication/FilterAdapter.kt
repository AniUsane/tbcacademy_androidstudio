package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FilterLayoutBinding

class FilterAdapter(val dataList: List<FilterClass>,
                    private val onFilterSelected: (FilterStatus) -> Unit):
                     RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            FilterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.onBind()
    }


    inner class FilterViewHolder(val binding: FilterLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(){
            val model: FilterClass = dataList[adapterPosition]
            binding.filterButton.text = model.filterStatus.name

            binding.filterButton.setOnClickListener{
                onFilterSelected(model.filterStatus)
            }
        }
    }

}