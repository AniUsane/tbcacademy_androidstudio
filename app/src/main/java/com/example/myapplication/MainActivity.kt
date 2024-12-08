package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.Users

class MainActivity : AppCompatActivity() {
    private var usersList = mutableMapOf<String, String>()
    private var count = 0;
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateFields()

    }

    private fun addUser(){
        val email = binding.email.text.toString()
        val name = binding.fullName.text.toString()

        if(usersList.containsKey(email)){
            binding.userCount.text = "The user with this email already exists!"
        }else{
                usersList[email] = name
                count += 1
                binding.userCount.text = "User added."
        }

    }


    private fun validateFields() {
        binding.addBtn.setOnClickListener {
            if(binding.fullName.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches()) {
                addUser()
                binding.nameText.text = ""
                binding.emailText.text = ""
            }else if(binding.fullName.text.isNotEmpty()){
                binding.nameText.text = ""
            }else if(binding.fullName.text.isEmpty()){
                binding.nameText.text = "Full name field is empty."            }

            if(binding.email.text.isEmpty()) {
                binding.emailText.text = "Email field is empty."
            }else if(Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches() && binding.fullName.text.isEmpty()){
                binding.emailText.text = ""
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches() && binding.email.text.isNotEmpty()){
                binding.emailText.text = "Incorrect email format."
            }

        }

    }
}


