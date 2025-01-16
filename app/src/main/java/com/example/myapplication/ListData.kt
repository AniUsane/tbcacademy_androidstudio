package com.example.myapplication

import kotlinx.serialization.Serializable

@Serializable
data class ListData(
    val fields: List<FieldDataDto>
)
