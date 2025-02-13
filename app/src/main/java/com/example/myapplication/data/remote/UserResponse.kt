package com.example.myapplication

import com.example.myapplication.data.remote.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val data: List<User>
)