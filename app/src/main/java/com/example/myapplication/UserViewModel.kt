package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.AppContainer
import com.example.myapplication.data.AppDataContainer
import com.example.myapplication.data.User
import com.example.myapplication.data.UserRepository
import kotlinx.coroutines.flow.Flow

class UserViewModel(applicationContext: Application): AndroidViewModel(applicationContext) {
    private val userRepository:UserRepository = AppDataContainer(applicationContext).userRepository

//    val allUsers = userRepository.getAllItemsStream()
    val allUsers: Flow<List<User>> = userRepository.getAllItemsStream()



}