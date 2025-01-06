package com.example.myapplication

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentGameBinding
import java.util.UUID

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var selectedDimension: Int = 3
    private lateinit var itemsAdapter: ItemsAdapter
    private var itemList = mutableListOf<Items>()
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let {
            selectedDimension = it.getInt("selectedDimension", 3)
        }
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),selectedDimension)
        createGrid()

        itemsAdapter = ItemsAdapter {selectedItem -> handleItemClick(selectedItem)}
        binding.recyclerView.adapter = itemsAdapter

        itemsAdapter.submitList(itemList)


//        this method crashes the app because there is no winner when GameFragment is created
//        if(checkWinner()) {
//
//            val bundle = Bundle()
//            val resultFragment = ResultFragment()
//            bundle.putBoolean("checksWinner", checkWinner())
//
//            resultFragment.arguments = bundle
//            d("GameFragment", "We have a winner!")
//            parentFragmentManager.beginTransaction().apply {
//                replace(R.id.result_fragment, resultFragment)
//                addToBackStack("ResultFragment")
//                commit()
//            }
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createGrid(){
        val gridSize = selectedDimension * selectedDimension

        itemList.clear()
        for (i in 0 until gridSize) {
            itemList.add(Items(UUID.randomUUID(), R.drawable.white))
        }
    }

    private fun handleItemClick(item: Items) {


        clickCount++

        val updatedItemList = itemList.map {
            if (it.id == item.id) {
                if (clickCount % 2 == 0) {
                    it.copy(image = R.drawable.o)
                } else {
                    it.copy(image = R.drawable.x)
                }
            } else {
                it
            }
        }.toMutableList()

        itemList = updatedItemList
        itemsAdapter.submitList(itemList)
    }

    private fun checkWinner(): Boolean{
        for (row in 0 until selectedDimension) {
            if (isRowSame(row)) {
                return true
            }
        }

        for(column in 0 until selectedDimension){
            if(isColumnSame(column)){
                return true
            }
        }

        if(isDiagonalSame()){
            return true
        }

        return false
    }

    private fun isRowSame(row: Int):Boolean {

        for(col in 1 until selectedDimension){
            if(itemList[row * selectedDimension + col].image !=
                itemList[row * selectedDimension].image){
                return false
            }
        }
        return true

    }

    private fun isColumnSame(column: Int): Boolean {
        for (row in 1 until selectedDimension) {
            if (itemList[row * selectedDimension + column].image != itemList[column].image) {
                return false
            }
        }
        return true
    }

    private fun isDiagonalSame(): Boolean {
        for (i in 0 until selectedDimension) {
            if (itemList[i * selectedDimension + i].image != itemList[0].image) {
                return false
            }
        }

        for (i in 0 until selectedDimension) {
            if (itemList[i * selectedDimension + (selectedDimension - 1 - i)].image != itemList[selectedDimension - 1].image) {
                return false
            }
        }

        return true
    }


}