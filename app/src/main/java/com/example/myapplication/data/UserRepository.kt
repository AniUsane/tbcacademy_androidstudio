package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllItemsStream(): Flow<List<User>>
}