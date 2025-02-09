package com.example.myapplication.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)
