package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoggedOutBinding


class LoggedOutActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoggedOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Goes to LogIn activity when "Log In" button is pressed
        binding.logInButton.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        //Goes to Register activity when "Register" button is pressed
        binding.registerButton.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

}
