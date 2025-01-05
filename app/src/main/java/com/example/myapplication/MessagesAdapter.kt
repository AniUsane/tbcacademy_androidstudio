package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R.drawable.*
import com.example.myapplication.databinding.RecyclerChatBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class MessagesDiffUtil: DiffUtil.ItemCallback<MessagesData>(){
    override fun areItemsTheSame(oldItem: MessagesData, newItem: MessagesData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessagesData, newItem: MessagesData): Boolean {
        return oldItem == newItem
    }

}

class MessagesAdapter: ListAdapter<MessagesData, MessagesAdapter.MessagesViewHolder>(MessagesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        return MessagesViewHolder(
            RecyclerChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        val model = getItem(position)
        holder.onBind(model)
    }

    class MessagesViewHolder(private val binding: RecyclerChatBinding):
        RecyclerView.ViewHolder(binding.root){
            fun onBind(model: MessagesData) {

                binding.messageTime.text = formatTime(model.time)

                binding.messages.text = model.message

                setConstraintsForRight(model.rightAligned)

            }

        private fun setConstraintsForRight(rightAligned: Boolean){
            val constraintSet = ConstraintSet()
            val parent = binding.root
            constraintSet.clone(parent)

            if(rightAligned){
                binding.messages.setBackgroundResource(sent_message_style)
                constraintSet.connect(
                    binding.messages.id, ConstraintSet.END,
                    parent.id, ConstraintSet.END
                )
                constraintSet.connect(
                    binding.messageTime.id, ConstraintSet.END,
                    parent.id, ConstraintSet.END
                )
                constraintSet.clear(binding.messages.id, ConstraintSet.START)
                constraintSet.clear(binding.messageTime.id, ConstraintSet.START)
            }else{
                binding.messages.setBackgroundResource(received_message_style)
                constraintSet.connect(
                    binding.messages.id, ConstraintSet.START,
                    parent.id, ConstraintSet.START
                )
                constraintSet.connect(
                    binding.messageTime.id, ConstraintSet.START,
                    parent.id, ConstraintSet.START
                )
                constraintSet.clear(binding.messages.id, ConstraintSet.END)
                constraintSet.clear(binding.messageTime.id, ConstraintSet.END)
            }
            constraintSet.applyTo(parent)
        }

        private fun formatTime(date: Date): String? {
            val calendar = Calendar.getInstance()
            val today = Calendar.getInstance()
            val yesterday = Calendar.getInstance()

            calendar.time = date
            yesterday.add(Calendar.DAY_OF_YEAR, -1)

            val isToday = calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                    calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)
            val wasYesterday = calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR)&&
                    calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)

            val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())

            return when {
                isToday -> "Today, ${timeFormat.format(date)}"
                wasYesterday -> "Yesterday, ${timeFormat.format(date)}"
                else -> {
                    val dateFormat = SimpleDateFormat("MMM dd, h:mm a", Locale.getDefault())
                    dateFormat.format(date)
                }
            }
        }

    }
}
