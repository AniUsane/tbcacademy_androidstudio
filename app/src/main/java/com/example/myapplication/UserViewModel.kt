package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserViewModel(private val userInfoRepository: UserInfoRepository): ViewModel() {
    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo: StateFlow<UserInfo?> = _userInfo

    //Saves user info
    fun saveUser(firstName: String, lastName: String, email: String){
        viewModelScope.launch {
            userInfoRepository.saveUser(firstName, lastName, email)
        }
    }

    //Prints user info
    fun printUserInfo() {
        viewModelScope.launch {
            _userInfo.value = userInfoRepository.userInfo.first()
        }
    }
}