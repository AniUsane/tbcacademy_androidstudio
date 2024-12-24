package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.logging.Filter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var filterRecyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var filterList: ArrayList<FilterDataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var priceList: Array<String>
    lateinit var categoryList: Array<String>
    lateinit var filterAdapter: FilterAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addItems()
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        getData()

        getFilterData()
    }



    private fun getData(){
        for(i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i], priceList[i], categoryList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }

    private fun getFilterData(){
        recyclerView = findViewById(R.id.filter_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        filterList = ArrayList()
        addDataToFilter()
        filterAdapter = FilterAdapter(filterList)
        recyclerView.adapter = filterAdapter

    }

    private fun addDataToFilter(){
        filterList.add(FilterDataClass("All"))
        filterList.add(FilterDataClass("Party"))
        filterList.add(FilterDataClass("Camping"))
        filterList.add(FilterDataClass("Category1"))
        filterList.add(FilterDataClass("Category2"))
        filterList.add(FilterDataClass("Category3"))
    }

    private fun addItems(){
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

        categoryList = arrayOf(
            "Party",
            "Party",
            "Camping",
            "Camping",
            "Category1",
            "Category1",
            "Category2",
            "Category2",
            "Category3",
            "Category3",
            "Party",
            "Camping",
            "Category1",
            "Category2",
            "Category3",
            "Party"
        )
    }
}

