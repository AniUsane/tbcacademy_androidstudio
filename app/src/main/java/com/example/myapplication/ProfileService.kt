package com.example.myapplication

import com.example.myapplication.logInPage.LogInResponse
import com.example.myapplication.registerPage.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileService {

    @POST("/api/login")
    suspend fun postLogin(@Body loginRequest: UserInfo): Response<LogInResponse>

    @POST("/api/register")
    suspend fun postRegister(@Body registerRequest: UserInfo): Response<RegisterResponse>
}