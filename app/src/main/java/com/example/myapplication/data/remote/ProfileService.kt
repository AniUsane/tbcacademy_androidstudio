package com.example.myapplication.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileService {
    @POST("api/login")
    suspend fun logIn(@Body loginRequest: Request): Response<LoginResponse>

    @POST("api/register")
    suspend fun register(@Body registerRequest: Request): Response<RegisterResponse>
}