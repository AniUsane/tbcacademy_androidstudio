package com.example.myapplication.presentation.feed.post

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Posts(
    val id: Int,
    val images: List<String>? = null,
    val title: String,
    val comments: Int? = null,
    val likes: Int? = null,
    @SerialName("share_content")
    val shareContent: String,
    val owner: Owner
)

@Serializable
data class Owner(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String? = null,
    val profile: String? = null,
    @SerialName("post_date")
    val postDate: Long
)

