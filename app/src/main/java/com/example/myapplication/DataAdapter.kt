package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemFieldBinding

class FieldAdapter(private val fields: List<FieldDataDto>,
                   private val viewModel: DataViewModel,
                   private val getInputType: (String?) -> Int) : RecyclerView.Adapter<FieldAdapter.FieldViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldViewHolder {
        return FieldViewHolder(
            ItemFieldBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FieldViewHolder, position: Int) {
        holder.onBind(fields[position])
    }

    override fun getItemCount(): Int = fields.size


    inner class FieldViewHolder(private val binding: ItemFieldBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(field: FieldDataDto) {
            binding.editText.inputType = viewModel.getInputType(field.keyboard ?: "text")
            binding.editText.visibility = if (field.fieldType == "input") View.VISIBLE else View.GONE
            binding.spinner.visibility = if (field.fieldType == "chooser") View.VISIBLE else View.GONE
            binding.fieldHint.text = field.hint
            loadImage(field.icon, binding.fieldIcon)

        }
        fun loadImage(imageUrl: String, imageView: ImageView) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }
}

