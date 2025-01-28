package com.example.myapplication.homePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.User
import kotlinx.coroutines.flow.Flow

class HomeViewModel(repository: UserRepository):ViewModel() {
    val userList: Flow<PagingData<User>> = repository.getUserPaging().cachedIn(viewModelScope)
}