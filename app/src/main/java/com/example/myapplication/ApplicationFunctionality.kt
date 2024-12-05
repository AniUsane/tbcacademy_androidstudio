package com.example.myapplication

import android.widget.EditText
import android.widget.TextView
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ApplicationFunctionality(
    val emailField: EditText,
    val usernameField: EditText,
    val firstNameField: EditText,
    val lastNameField: EditText,
    val ageField: EditText
):AppCompatActivity() {
    private val messages = mutableListOf<String>()
//    private val emailField = findViewById<EditText>(R.id.email)
//    val usernameField = findViewById<EditText>(R.id.username)
//    val firstNameField = findViewById<EditText>(R.id.first_name)
//    val lastNameField = findViewById<EditText>(R.id.last_name)
//    val ageField = findViewById<EditText>(R.id.age)
//    val text1 = findViewById<TextView>(R.id.text1)

    private fun filledFields(fieldName: EditText): String {
        //Checks whether fields are empty or not, saves messages into a list and returns messages
        val emailField = findViewById<EditText>(R.id.email)
        val usernameField = findViewById<EditText>(R.id.username)
        val firstNameField = findViewById<EditText>(R.id.first_name)
        val lastNameField = findViewById<EditText>(R.id.last_name)
        val ageField = findViewById<EditText>(R.id.age)
        val text1 = findViewById<TextView>(R.id.text1)
        //val messages = mutableListOf<String>()

        if (emailField.text.isEmpty()) {
            messages.add("Email field is empty.")
        }
        if (usernameField.text.isEmpty()) {
            messages.add("Username field is empty.")
        }
        if (firstNameField.text.isEmpty()) {
            messages.add("First name field is empty.")
        }
        if(lastNameField.text.isEmpty()) {
            messages.add("Last name field is empty.")
        }
        if(ageField.text.isEmpty()) {
            messages.add("Age field is empty.")
        }

        return if (messages.isNotEmpty()) {
            text1.text = messages.joinToString("\n")
            text1.text.toString()
        } else {
            ""
        }
    }
}