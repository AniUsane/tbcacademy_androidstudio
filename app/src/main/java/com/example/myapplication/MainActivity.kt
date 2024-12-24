package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var priceList: Array<String>

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageList = arrayOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4)

        titleList = arrayOf(
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer",
            "Belt suit blazer")

        priceList = arrayOf(
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ",
            "\$120 ")

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        getData()
    }



    private fun getData(){
        for(i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i], priceList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }
}

