package com.example.myapplication

import com.example.myapplication.logInPage.LogInResponse
import com.example.myapplication.registerPage.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileService {

    @POST("/api/login")
    suspend fun postLogin(@Body loginRequest: UserInfo): Response<LogInResponse>

    @POST("/api/register")
    suspend fun postRegister(@Body registerRequest: UserInfo): Response<RegisterResponse>

    @GET("/api/users")
    suspend fun getUserList(@Query("page") page: Int): UserResponse
}