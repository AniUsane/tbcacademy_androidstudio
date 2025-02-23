package com.example.myapplication.presentation.feed.story

import kotlinx.serialization.Serializable

@Serializable
data class Story(
    val id: Int,
    val cover: String,
    val title: String
)