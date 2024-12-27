package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.AddressCardsBinding

class AddressDiffUtil: DiffUtil.ItemCallback<AddressClass>(){
    override fun areItemsTheSame(oldItem: AddressClass, newItem: AddressClass): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AddressClass, newItem: AddressClass): Boolean {
        return oldItem == newItem
    }

}

class ItemAdapter(val items: List<AddressClass>): ListAdapter<AddressClass, ItemAdapter.AddressViewHolder>(AddressDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(
            AddressCardsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }




    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.onBind()
    }

    inner class AddressViewHolder(val binding: AddressCardsBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBind(){
            val model: AddressClass = items[adapterPosition]
            binding.address.text = model.address
            binding.addressName.text = model.addressName
            binding.cardIcon.setImageResource(model.dataImage)

            binding.radioBtn.setOnFocusChangeListener { v, hasFocus ->
                // When the radio button gains focus, show the editLink
                if (hasFocus) {
                    binding.editLink.visibility = View.VISIBLE
                } else {
                    // Optionally hide the editLink when the radio button loses focus
                    binding.editLink.visibility = View.GONE
                }

            }

            binding.root.setOnLongClickListener {
                removeItem(adapterPosition)
                true
            }

        }



        private fun removeItem(position: Int) {
            (items as MutableList).removeAt(position)

            notifyItemRemoved(position)

        }


    }


}






