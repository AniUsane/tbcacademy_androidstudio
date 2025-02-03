package com.example.myapplication.retrofit

import com.example.myapplication.UserResponse
import retrofit2.http.GET

interface ProfileService {
    @GET("v3/f3f41821-7434-471f-9baa-ae3dee984e6d")
    suspend fun getAllUsers(): UserResponse

}