package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val usersList = mutableMapOf<String, Users>()
    private val removedUsers = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addUserBtn.setOnClickListener{
            checkFields()
            addUser()
            binding.activeUsers.text = "Online Users: ${usersList.size}"

        }

        binding.removeUserBtn.setOnClickListener{
            removeUser()
            binding.removedUsers.text = "Removed Users: ${removedUsers.size}"
            binding.activeUsers.text = "Online Users: ${usersList.size}"
        }

        binding.updateUserBtn.setOnClickListener{
            updateUser()
        }

    }

    private fun addUser(){
        if(binding.email.text?.isEmpty() == true || binding.firstName.text?.isEmpty() == true || binding.lastName.text?.isEmpty() == true || binding.age.text?.isEmpty() == true){
            binding.identificationTxt.setTextColor(Color.parseColor("#F44336"))
            binding.identificationTxt.text = "Please fill in all the fields."
        }else if(usersList.contains(binding.email.text.toString())) {
            binding.identificationTxt.setTextColor(Color.parseColor("#F44336"))
            binding.identificationTxt.text = "User already exists."
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches()){
            binding.identificationTxt.setTextColor(Color.parseColor("#F44336"))
            binding.identificationTxt.text = "Please write the correct email address."
        }else{
            usersList.put(binding.email.text.toString(), Users(name = binding.firstName.text.toString(), lastName = binding.lastName.text.toString(), age = binding.age.text.toString()))
            binding.identificationTxt.setTextColor(Color.parseColor("#4CAF50"))
            binding.identificationTxt.text = "User added successfully."
        }
    }

    private fun checkFields(){
        if(binding.email.text?.isEmpty() == true){
            binding.email.error = "Enter your email"
        }
        if(binding.firstName.text?.isEmpty() == true)
            binding.firstName.error = "Enter your first name"
        if(binding.lastName.text?.isEmpty() == true)
            binding.lastName.error = "Enter your last name"
        if(binding.age.text?.isEmpty() == true)
            binding.age.error = "Enter your age"
    }

    private fun removeUser(){
        if(usersList.contains(binding.email.text.toString())) {
            usersList.remove(binding.email.text.toString())
            binding.identificationTxt.setTextColor(Color.parseColor("#4CAF50"))
            binding.identificationTxt.text = "User deleted successfully."
            removedUsers.add(binding.email.text.toString())

        }else{
            binding.identificationTxt.setTextColor(Color.parseColor("#F44336"))
            binding.identificationTxt.text = "User does not exist."
        }
    }

    private fun updateUser(){
        if(usersList.contains(binding.email.text.toString())){
            usersList[binding.email.text.toString()] = Users(name = binding.firstName.text.toString(), binding.lastName.text.toString(), age = binding.age.text.toString())
            binding.identificationTxt.setTextColor(Color.parseColor("#4CAF50"))
            binding.identificationTxt.text = "User information updated successfully."

        }else{
            binding.identificationTxt.setTextColor(Color.parseColor("#F44336"))
            binding.identificationTxt.text = "User does not exist."
        }
    }
}

