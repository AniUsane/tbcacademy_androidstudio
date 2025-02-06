package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerViewBinding

class NumpadAdapter(private val buttons: List<NumpadClass>,
                    private val clickButton: (Int) -> Unit,
                    private val deleteButtonClicked: () -> Unit):
    RecyclerView.Adapter<NumpadAdapter.NumpadViewHolder>() {

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
                if (num.input in 0..9) {
                    text = num.input.toString()
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                }else if(num.input == R.drawable.fingerprint) {
                    text = ""
                    setCompoundDrawablesWithIntrinsicBounds(0, num.input, 0, 0)
                    compoundDrawablePadding = 0
                    setPadding(0, 40, 0, 0)
                }else {
                    text = ""
                    setCompoundDrawablesWithIntrinsicBounds(0, num.input, 0, 0)
                    compoundDrawablePadding = 0
                    setPadding(0, 80, 0, 0)
                }

                setOnClickListener {
                    if (num.input == R.drawable.delete_btn) {
                        deleteButtonClicked()
                    }else if(num.input == R.drawable.fingerprint){
                    }else {
                        clickButton(num.input)
                    }
                }
            }
        }
    }

}