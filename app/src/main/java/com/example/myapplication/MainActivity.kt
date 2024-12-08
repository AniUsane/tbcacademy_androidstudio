package com.example.myapplication

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Map is used to save the user info, key is email, and value is user name.
    private var usersList = mutableMapOf<String, String>()
    private var count = 0;
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateFields()
    }

    //this function saves user info into map
    private fun addUser(){
        val email = binding.email.text.toString()
        val name = binding.fullName.text.toString()

        if(usersList.containsKey(email)){
            binding.confirmationMsg.text = "The user with this email already exists!"
        }else{
            usersList[email] = name
            binding.confirmationMsg.text = "User added."
        }

        getUserInfo()

    }

    //Checks the email and returns according response whether it is saved or not.
    private fun getUserInfo() {
        binding.getUserInfoBtn.setOnClickListener{
            var email = binding.email.text.toString()
            if(usersList.containsKey(email)){
                binding.userInfo.text = "User name is: ${usersList[email]}"
            }else if(binding.email.text.toString() == ""){
                binding.userInfo.text = ""
            }else{
                binding.userInfo.text = "User not found with this email."
            }
        }
    }

    //validates fields and adds functionality for the GetUserInfo button without saving the user info
    private fun validateFields() {
        getUserInfo()
        binding.userCount.text = "Users -> $count"
        binding.addBtn.setOnClickListener {
            if(binding.fullName.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches()) {
                addUser()
                count += 1
                binding.userCount.text = "Users -> $count"
                binding.nameText.text = ""
                binding.emailText.text = ""
            }else if(binding.fullName.text.isNotEmpty()){
                binding.nameText.text = ""
            }else if(binding.fullName.text.isEmpty()){
                binding.nameText.text = "Full name field is empty."
            }

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


