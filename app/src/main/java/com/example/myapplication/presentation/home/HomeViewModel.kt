package com.example.myapplication.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.remote.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeViewModel @Inject constructor(repository: UserRepository): ViewModel() {
    val userList: Flow<PagingData<User>> = repository.getUserPaging().cachedIn(viewModelScope)
}