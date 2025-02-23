package com.example.myapplication.data.remote

import com.example.myapplication.presentation.feed.post.Posts
import com.example.myapplication.presentation.feed.story.Story
import retrofit2.http.GET

interface ProfileService {

    @GET("00a18030-a8c7-47c4-b0c5-8bff92a29ebf")
    suspend fun getStories(): List<Story>

    @GET("1ba8b612-8391-41e5-8560-98e4a48decc7")
    suspend fun getPosts(): List<Posts>

}