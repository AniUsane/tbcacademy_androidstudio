package com.example.myapplication.homePage

import android.util.Log.d
import com.example.myapplication.ProfileService
import com.example.myapplication.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(private val service: ProfileService) {
    fun getUserList(): Flow<List<User>> = flow {
        try {
            val response = service.getUserList()
            d("UserRepository", "API Response: ${response.data}")
            emit(response.data)
        } catch (e: Exception) {
            d("UserRepository", "Error fetching users: ${e.message}")
            emit(emptyList())
        }
    }
}