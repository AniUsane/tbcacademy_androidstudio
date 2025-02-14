package com.example.myapplication

data class CardData(
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val location: String,
    val reaction_count: Int,
    val rate: Float = 0f
)
