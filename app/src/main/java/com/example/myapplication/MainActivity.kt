package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var items = mutableListOf(
        AddressClass(
            id = 1,
            dataImage = R.drawable.office,
            addressName = "My Office",
            address = "SBI Building, street 3, Software Park"
        ),
        AddressClass(
            id = 2,
            dataImage = R.drawable.home,
            addressName = "My Home",
            address = "SBI Building, street 3, Software Park"
        )

    )

    



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ItemAdapter(items)

        val addAddressFragment = AddNewAddressFragment()

        val adapter = ItemAdapter(items)
        binding.recyclerView.adapter = adapter


        binding.addNewAddress.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment1, addAddressFragment)
            addToBackStack("AddressRecyclerView")
                binding.recyclerView.visibility = View.GONE
                binding.addNewAddress.visibility = View.GONE
                binding.title.text = "Add new delivery address"
            commit()
            }
        }





    }
}

