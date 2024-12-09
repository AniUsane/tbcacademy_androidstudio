package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val inputs = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveWord()
        printAnagrams()
        clearActivity()

    }
    //function saves words if the input field is not empty
    private fun saveWord() {
        binding.saveBtn.setOnClickListener {
            if(binding.anagram.text.toString() == "")
                binding.viewAnagrams.text = "Please enter input in the field."
            else{
                inputs.add(binding.anagram.text.toString())
                binding.viewAnagrams.text = "Added word to the container."
            }
        }
    }
    //Checks and sorts anagrams and prints them by groups
    private fun printAnagrams() {
        binding.outputBtn.setOnClickListener {
            if(inputs.isNotEmpty()) {
                //groups elements according to size of the characters
                binding.viewAnagrams.text = inputs.groupBy { it.length }.values.toString()
                //sorts sized characters according to the same characters
                binding.viewAnagrams.text = inputs.groupBy { it.toCharArray().sorted() }.values.toString()
            }else
                binding.viewAnagrams.text = "List is empty."

        }
    }
    //function clears input field, text view field and clears list as well after clear button is pressed.
    private fun clearActivity(){
        binding.clearBtn.setOnClickListener{
            binding.anagram.text.clear()
            inputs.clear()
            binding.viewAnagrams.text = ""
        }
    }

}
