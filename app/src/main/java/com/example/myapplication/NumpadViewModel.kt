package com.example.myapplication

import androidx.lifecycle.ViewModel

class NumpadViewModel: ViewModel() {
    val numpadButtons = listOf(
        NumpadClass(1), NumpadClass(2), NumpadClass(3), NumpadClass(4),
        NumpadClass(5), NumpadClass(6), NumpadClass(7), NumpadClass(8),
        NumpadClass(9), NumpadClass(R.drawable.fingerprint), NumpadClass(0),
        NumpadClass(R.drawable.delete_btn)
    )
}