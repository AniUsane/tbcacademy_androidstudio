package com.example.myapplication.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.repository.PostRepository
import com.example.myapplication.presentation.feed.post.Posts
import com.example.myapplication.presentation.feed.story.Story
import com.example.myapplication.data.remote.repository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val storyRepository: StoryRepository
) : ViewModel() {
    private val _stories = MutableStateFlow<List<Story>>(emptyList())
    val stories: StateFlow<List<Story>> = _stories.asStateFlow()

    private val _posts = MutableStateFlow<List<Posts>>(emptyList())
    val posts: StateFlow<List<Posts>> = _posts.asStateFlow()

    fun getStories(){
        viewModelScope.launch {
            storyRepository.getStories().collect { storyList ->
                _stories.value = storyList
            }
        }
    }

    fun getPosts(){
        viewModelScope.launch {
            postRepository.getPosts().collect { postList ->
                _posts.value = postList
            }
        }
    }
}