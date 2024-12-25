package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(private val filterList: List<FilterDataClass>):
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): FilterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filters_layout, parent, false)
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filterButton = filterList[position]
        holder.rvFilterButton.text = filterButton.filterName
        holder.rvFilterEmoji.text = filterButton.filterEmoji
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    class FilterViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val rvFilterButton: TextView = itemView.findViewById(R.id.filter_button)
        val rvFilterEmoji: TextView = itemView.findViewById(R.id.filter_emoji)
    }
}