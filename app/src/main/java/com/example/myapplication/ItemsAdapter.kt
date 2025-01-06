package com.example.myapplication

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentConfigBinding
import com.example.myapplication.databinding.RecyclerViewBinding


class ItemsAdapter(val adapterList: List<Items>): RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    inner class ItemsViewHolder(private val binding: FragmentConfigBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            FragmentConfigBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        fun onBind(){

        }
    }
}