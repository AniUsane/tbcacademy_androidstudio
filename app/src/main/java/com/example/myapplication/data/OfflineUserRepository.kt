package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao:UserDao): UserRepository {
    override fun getAllItemsStream(): Flow<List<User>> = userDao.getAllItems()
}