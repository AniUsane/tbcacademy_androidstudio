package com.example.myapplication

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentLockScreenBinding

class LockScreenFragment : BaseFragment<FragmentLockScreenBinding>(FragmentLockScreenBinding::inflate) {
    private val viewModel: NumpadViewModel by viewModels()

    override fun start() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = NumpadAdapter(viewModel.numpadButtons, { number ->
            enterPassword(number)}, {deleteNumber()} )
        checkPassword()
    }

    val enteredPassword = mutableListOf<Int>()

    private fun enterPassword(number: Int){
        if (enteredPassword.size < 4) {
            enteredPassword.add(number)
            updateDots()

            if (enteredPassword.size == 4) {
                checkPassword()
            }
        }
    }

    private fun checkPassword(){
        if (enteredPassword.joinToString("") == "0934"){
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            enteredPassword.clear()
            defaultDots()
        }else{
            Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
            enteredPassword.clear()
            defaultDots()
            deleteNumber()
        }
    }

    private fun defaultDots(){
        binding.dot1.setBackgroundResource(R.drawable.empty_dot_background)
        binding.dot2.setBackgroundResource(R.drawable.empty_dot_background)
        binding.dot3.setBackgroundResource(R.drawable.empty_dot_background)
        binding.dot4.setBackgroundResource(R.drawable.empty_dot_background)
    }

    private fun updateDots() {
        val dots = listOf(binding.dot1, binding.dot2, binding.dot3, binding.dot4)
        for (i in dots.indices) {
            if (i < enteredPassword.size) {
                dots[i].setBackgroundResource(R.drawable.filled_dot_background)
            } else {
                dots[i].setBackgroundResource(R.drawable.empty_dot_background)
            }
        }
    }

    private fun deleteNumber(){
        if(enteredPassword.isNotEmpty()){
            enteredPassword.removeAt(enteredPassword.size-1)
            updateDots()
        }
    }



}