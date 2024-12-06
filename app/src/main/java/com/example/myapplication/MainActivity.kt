package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {



    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Checks empty fields and prints messages
        filledFields()
        clearFields()
    }

    @SuppressLint("SetTextI18n")
    private fun filledFields() {
        //Checks whether fields are empty or not, saves messages into a list and returns messages
        val emailField = findViewById<EditText>(R.id.email)
        val usernameField = findViewById<EditText>(R.id.username)
        val firstNameField = findViewById<EditText>(R.id.first_name)
        val lastNameField = findViewById<EditText>(R.id.last_name)
        val ageField = findViewById<EditText>(R.id.age)
        val text1 = findViewById<TextView>(R.id.text1)
        text1.isFocusable = false
        val saveButton = findViewById<Button>(R.id.save_btn)
        val messages = mutableListOf<String>()
        val text2 = findViewById<TextView>(R.id.text2)
        text2.isFocusable = false
        val text3 = findViewById<TextView>(R.id.text3)
        text3.isFocusable = false
        val text4 = findViewById<TextView>(R.id.text4)
        text4.isFocusable = false
        val text5 = findViewById<TextView>(R.id.text5)
        text5.isFocusable = false

        saveButton.setOnClickListener {
            if (emailField.text.isEmpty()) {
                text1.text = "Email field is empty."
                messages.add(text1.text.toString())
            }else{
                text1.text = ""
                messages.add(text1.text.toString())
            }

            if (usernameField.text.isEmpty()) {
                text2.text = "Username field is empty."
                messages.add(text2.text.toString())
            }else{
                text2.text = ""
                messages.add(text2.text.toString())
            }

            if (firstNameField.text.isEmpty()) {
                text3.text = "First name field is empty."
                messages.add(text3.text.toString())
            }else{
                text3.text = ""
                messages.add(text3.text.toString())
            }

            if (lastNameField.text.isEmpty()) {
                text4.text = "Last name field is empty."
                messages.add(text4.text.toString())
            }else{
                text4.text = ""
                messages.add(text4.text.toString())
            }

            if (ageField.text.isEmpty()) {
                text5.text = "Age field is empty."
                messages.add(text5.text.toString())
            }else{
                text5.text = ""
                messages.add(text5.text.toString())
            }

            if (emailField.text.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(emailField.text.toString())
                    .matches()
            ) {
                text1.text = "Invalid email address."
                messages.add(text1.text.toString())
            }else{
                text1.text = ""
                messages.add(text1.text.toString())
            }

            if (usernameField.text.isNotEmpty() && usernameField.text.length < 10) {
                text2.text = "Username should have more than 10 symbols."
                messages.add(text2.text.toString())
            }else{
                text2.text = ""
                messages.add(text2.text.toString())
            }
            val string1 = text1.text

            if (!(text1.text.toString() == "" && text2.text.toString() == "" && text3.text.toString() == "" && text4.text.toString() == "" && text5.text.toString() == "")) {
                text1.text.toString()
                text2.text.toString()
                text3.text.toString()
                text4.text.toString()
                text5.text.toString()
            }else{
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("email", emailField.text.toString());
                intent.putExtra("username", usernameField.text.toString())
                intent.putExtra("first_name", firstNameField.text.toString())
                intent.putExtra("last_name", lastNameField.text.toString())
                intent.putExtra("age", ageField.text.toString())
                startActivity(intent)
            }
        }
    }


//    When "Clear" button is pressed for a long time all field inputs are deleted
    private fun clearFields() {
        val emailField = findViewById<EditText>(R.id.email)
        val usernameField = findViewById<EditText>(R.id.username)
        val firstNameField = findViewById<EditText>(R.id.first_name)
        val lastNameField = findViewById<EditText>(R.id.last_name)
        val ageField = findViewById<EditText>(R.id.age)
        val clearButton = findViewById<Button>(R.id.clear_btn)
        clearButton.setOnLongClickListener{
            emailField.text.clear()
            usernameField.text.clear()
            firstNameField.text.clear()
            lastNameField.text.clear()
            ageField.text.clear()
            true
        }
    }
}



