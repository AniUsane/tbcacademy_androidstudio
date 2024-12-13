package com.example.myapplication

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }

    //It does not work properly
    private fun gradient(){
        val paint = binding.mainText.paint
        paint.shader = LinearGradient(
            0f, 0f, 0f, binding.mainText.textSize, intArrayOf(
                Color.parseColor("#A5A5A5"),
                Color.parseColor("#00A5A5A5")
            ), null,Shader.TileMode.CLAMP
        )
    }
}

