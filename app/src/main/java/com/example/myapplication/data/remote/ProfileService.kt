package com.example.myapplication.data.remote

import com.example.myapplication.UserInfo
import com.example.myapplication.UserResponse
import com.example.myapplication.data.remote.LoginResponse
import com.example.myapplication.data.remote.RegisterResponse
import com.example.myapplication.data.remote.Request
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileService {
//
//    @POST("api/login")
//    suspend fun logIn(@Body loginRequest: Request): Response<LoginResponse>
//
//    @POST("api/register")
//    suspend fun register(@Body registerRequest: Request): Response<RegisterResponse>

    @POST("login")
    suspend fun postLogin(@Body loginRequest: UserInfo): Response<LoginResponse>

    @POST("register")
    suspend fun postRegister(@Body registerRequest: Request): Response<RegisterResponse>

    @GET("users")
    suspend fun getUserList(@Query("page") page: Int): Response<UserResponse>
}