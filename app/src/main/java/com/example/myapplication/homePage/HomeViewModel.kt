package com.example.myapplication.homePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository):ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                repository.getUserList().collect { userList ->
                    _users.value = userList
                }
            } catch (e: Exception) {
                _users.value = emptyList()
            }
        }
    }
}