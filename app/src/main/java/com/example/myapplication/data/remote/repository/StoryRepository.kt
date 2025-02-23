package com.example.myapplication.data.remote.repository


import com.example.myapplication.presentation.feed.story.Story
import com.example.myapplication.data.remote.ProfileService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoryRepository @Inject constructor(private val api: ProfileService) {

    fun getStories(): Flow<List<Story>> = flow {
        try {
            val stories = api.getStories()
            emit(stories)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}