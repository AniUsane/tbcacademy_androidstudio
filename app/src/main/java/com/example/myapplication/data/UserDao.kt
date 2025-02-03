package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from items ORDER BY firstName ASC")
    fun getAllItems(): Flow<List<User>>
}