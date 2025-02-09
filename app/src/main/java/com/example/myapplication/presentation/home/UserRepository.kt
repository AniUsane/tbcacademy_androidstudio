package com.example.myapplication.presentation.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.data.remote.ProfileService
import com.example.myapplication.data.remote.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val service: ProfileService) {

    fun getUserPaging(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {UserPaging(service)}
        ).flow
    }
}