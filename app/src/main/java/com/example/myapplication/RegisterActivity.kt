package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Should be opening Register2Activity but it is reopening same activity
        binding.nextBtn.setOnClickListener{
            val intent = Intent(this, Register2Activity::class.java)
            intent.putExtras(intent)
            startActivity(intent)
        }

    }
}