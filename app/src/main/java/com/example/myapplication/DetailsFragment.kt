package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentDetailsBinding
import com.example.myapplication.databinding.FragmentPendingOrdersBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val orderNumber = it.getString("order number is: ")
            val trackingNumber = it.getString("tracking number is: ")
            val quantity = it.getString("quantity is: ")
            val total = it.getString("total is: ")
            val orderStatus = it.getString("order status is: ")
            val date = Date(it.getLong("date: "))

            binding.orderNumber.text = orderNumber
            binding.trackingNumber.text = trackingNumber
            binding.quantity.text = quantity.toString()
            binding.total.text = total
            binding.orderStatus.text = orderStatus
            binding.date.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}