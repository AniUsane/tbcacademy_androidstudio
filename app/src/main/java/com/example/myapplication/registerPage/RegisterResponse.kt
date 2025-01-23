package com.example.myapplication.registerPage

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id:Int,
    val token:String
)
