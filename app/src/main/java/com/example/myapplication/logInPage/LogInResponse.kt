package com.example.myapplication.logInPage

import kotlinx.serialization.Serializable

@Serializable
data class LogInResponse(
    val token:String
)
