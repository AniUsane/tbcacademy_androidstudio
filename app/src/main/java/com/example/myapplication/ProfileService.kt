package com.example.myapplication

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileService {

    @POST("/api/login")
    suspend fun postLogin(@Body loginRequest: UserInfo): Response<UserInfo>

    @POST("/api/register")
    suspend fun postRegister(@Body registerRequest: UserInfo): Response<UserInfo>
}