package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signInFragment = SignInFragment()
        val signUpFragment = SignUpFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.sign_in_fl, signInFragment)
            addToBackStack("SignInFragment")
            commit()        }

        binding.signUpLink.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.sign_in_fl, signUpFragment)
                binding.signUpText.visibility = View.GONE
                binding.signUpLink.visibility = (View.GONE)
                addToBackStack("SignUpFragment")
                commit()
            }
        }

        binding.backBtn.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.sign_in_fl, signInFragment)
                binding.signUpText.visibility = (View.VISIBLE)
                binding.signUpLink.visibility = (View.VISIBLE)
                addToBackStack("SignInFragment")
                commit()
            }
        }

    }
}

