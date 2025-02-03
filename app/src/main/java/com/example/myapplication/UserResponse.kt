package com.example.myapplication

import com.example.myapplication.data.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: Int,
    val avatar: String?,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val about: String?,
    @SerializedName("activation_status")
    val activeStatus: Int
)
