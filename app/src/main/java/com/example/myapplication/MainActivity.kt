package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val items = mutableListOf(
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
        binding.recyclerView.adapter = Adapter(items)

    }
}

