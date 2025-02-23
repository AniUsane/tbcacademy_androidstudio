package com.example.myapplication.data.remote.repository

import com.example.myapplication.presentation.feed.post.Posts
import com.example.myapplication.data.remote.ProfileService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostRepository @Inject constructor(private val api: ProfileService) {

    fun getPosts(): Flow<List<Posts>> = flow{
        try {
            val posts = api.getPosts()
            emit(posts)
        } catch (e: Exception){
            emit(emptyList())
        }
    }
}