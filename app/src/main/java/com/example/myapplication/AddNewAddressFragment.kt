package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.AddNewAddressBinding
import com.example.myapplication.databinding.AddressCardsBinding

class AddNewAddressFragment : Fragment(R.layout.add_new_address) {
    private lateinit var binding: AddNewAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment layout
        binding = AddNewAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.binding.backBtn.setOnClickListener {
            activity.supportFragmentManager.popBackStack()
            activity.binding.recyclerView.visibility = View.VISIBLE
            activity.binding.addNewAddress.visibility = View.VISIBLE
            activity.binding.title.text = "Delivery address"
        }

        binding.addAddress.setOnClickListener{
            activity.items.add(1, AddressClass(0, 0, binding.enterAddressName.text.toString(), binding.enterAddress.text.toString()))
            activity.supportFragmentManager.popBackStack()
            activity.binding.recyclerView.visibility = View.VISIBLE
            activity.binding.addNewAddress.visibility = View.VISIBLE
        }


    }

}