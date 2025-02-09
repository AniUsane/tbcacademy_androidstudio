package com.example.myapplication.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id: Int? = null,
    val token: String? = null
)
