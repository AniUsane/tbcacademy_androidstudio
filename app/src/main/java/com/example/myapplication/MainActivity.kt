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
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ApplicationFunctionality

class MainActivity : AppCompatActivity() {



    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Checks empty fields and prints messages
        checkEmptyFields()
        clearFields()
    }

    private fun filledFields(fieldName:EditText): String {
        //Checks whether fields are empty or not, saves messages into a list and returns messages
        val emailField = findViewById<EditText>(R.id.email)
        val usernameField = findViewById<EditText>(R.id.username)
        val firstNameField = findViewById<EditText>(R.id.first_name)
        val lastNameField = findViewById<EditText>(R.id.last_name)
        val ageField = findViewById<EditText>(R.id.age)
        val text1 = findViewById<TextView>(R.id.text1)
        val messages = mutableListOf<String>()

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

    private fun checkEmptyFields() {
        val emailField = findViewById<EditText>(R.id.email)
        val saveButton = findViewById<Button>(R.id.save_btn)

        saveButton.setOnClickListener{
            checkUsernameLength()
            validEmail()
            filledFields(emailField)
        }
    }
    //Function checkes whether username has more than 10 symbols or not and returns message
    private fun checkUsernameLength(): String {
        val text2 = findViewById<TextView>(R.id.text2)
        val usernameField = findViewById<EditText>(R.id.username)

        if(usernameField.text.length < 10 ) {
            text2.text = "Username Should have more than 10 symbols."
        }else{
            text2.text = ""
        }

        return text2.text.toString()
    }


    private fun validEmail(): String {
        val emailField = findViewById<EditText>(R.id.email)
        val saveButton = findViewById<Button>(R.id.save_btn)
        val text3 = findViewById<TextView>(R.id.text3)
        val messages = mutableListOf<String>()


        if (!Patterns.EMAIL_ADDRESS.matcher(emailField.text.toString()).matches()) {
            text3.text = "Invalid Email address."
        } else
            text3.text = ""

        return text3.text.toString()
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



