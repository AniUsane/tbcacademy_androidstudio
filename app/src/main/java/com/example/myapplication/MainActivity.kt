package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val input = findViewById<EditText>(R.id.input)
        val button = findViewById<Button>(R.id.button)
        val output = findViewById<TextView>(R.id.output)
        val toggle = findViewById<ToggleButton>(R.id.toggle)
        var isEnglish: Boolean = false
        toggle.setOnCheckedChangeListener { _, isChecked ->
            isEnglish = isChecked // Set language based on toggle
            val language = if (isChecked) "English" else "ქართული"
            Toast.makeText(this, "Language: $language", Toast.LENGTH_SHORT).show()
        }


        button.setOnClickListener {
            val inputByUser = input.text.toString()
            if(inputByUser.isEmpty()) {
                Toast.makeText(this, "შეიყვანე რიცხვი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val num = inputByUser.toInt()
            if (num < 1 || num > 1000) {
                Toast.makeText(this, "შეიყვანე რიცხვი [1-1000] შუალედში", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = if (isEnglish) {
                numToEng(num)
            } else {
                numToGeo(num)
            }

            // Show the result
            output.text = result

        }
    }

    private fun numToGeo(number:Int):String {
        val digits = arrayOf("", "ერთი", "ორი", "სამი", "ოთხი", "ხუთი", "ექვსი", "შვიდი", "რვა", "ცხრა")
        val overTen = arrayOf("თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი", "თხუთმეტი", "თექვსმეტი", "ჩვიდმეტი", "თვრამეტი", "ცხრამეტი")
        val tens = arrayOf("", "", "ოცი", "ოცდაათი", "ორმოცი", "ორმოცდაათი", "სამოცი", "სამოცდაათი", "ოთხმოცი", "ოთხმოცდაათი")
        val hundreds = arrayOf("", "ასი", "ორასი", "სამასი", "ოთხასი", "ხუთასი", "ექვსასი", "შვიდასი", "რვაასი", "ცხრაასი")
        if (number == 1000) return "ათასი"

        val hundred = number / 100
        val remainder = number % 100
        val ten = remainder / 10
        val unit = remainder % 10

        var result = ""
        if(hundred>0) result += hundreds[hundred]
        if (remainder in 10..19){
            result+=" " + overTen[remainder-10]
        }else{
            if(ten>0) result += " " + overTen[ten]
            if(unit>0) result += " " + digits[unit]
        }
        return result.trim()
    }

    private fun numToEng(number:Int):String {
        val digits = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val overTen = arrayOf("", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
        val tens = arrayOf("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
        val hundreds = arrayOf("", "one hundred", "two hundred", "three hundred", "four hundred", "five hundred", "six hundred", "seven hundred", "eight hundred", "nine hundred")
        if (number == 1000) return "one thousand"

//        val hundred = number / 100
//        val remainder = number % 100
//        val ten = remainder / 10
//        val unit = remainder % 10
//
//        var result = ""
        val hundred = number / 100
        val ten = (number % 100) / 10
        val unit = number % 10

        var result = ""
        if (hundred > 0) result += hundreds[hundred]
        if (ten > 0) result += " " + tens[ten]
        if (unit > 0) result += " " + digits.get(unit)

        return result.trim { it <= ' ' }
    }
}

