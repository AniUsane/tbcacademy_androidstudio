package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val email = findViewById<TextView>(R.id.view_text)
        val username = findViewById<TextView>(R.id.view_text2)
        val firstName = findViewById<TextView>(R.id.view_text3)
        val lastName = findViewById<TextView>(R.id.view_text4)
        val age = findViewById<TextView>(R.id.view_text5)

        val intent = intent

        val viewEmail = intent.getStringExtra("email")
        val viewUsername = intent.getStringExtra("username")
        val viewFirstName = intent.getStringExtra("first_name")
        val viewLastName = intent.getStringExtra("last_name")
        val viewAge = intent.getStringExtra("age")

        email.text = viewEmail
        username.text = viewUsername
        firstName.text = viewFirstName
        lastName.text = viewLastName
        age.text = viewAge
    }
}
