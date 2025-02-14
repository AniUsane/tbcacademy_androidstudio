package com.example.myapplication

import retrofit2.http.GET

interface CardService {

    @GET("6dffd14a-836f-4566-b024-bd41ace3a874")
    suspend fun getData(): List<CardData>

}