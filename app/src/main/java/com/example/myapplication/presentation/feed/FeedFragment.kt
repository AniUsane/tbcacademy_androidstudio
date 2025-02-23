package com.example.myapplication.presentation.feed

import android.util.Log.d
import android.util.Log.e
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.BaseFragment
import com.example.myapplication.presentation.feed.post.PostAdapter
import com.example.myapplication.presentation.feed.story.StoryAdapter
import com.example.myapplication.databinding.FragmentFeedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) {

    private val viewModel: FeedViewModel by viewModels()

    @Inject
    lateinit var storyAdapter: StoryAdapter
    @Inject
    lateinit var postAdapter: PostAdapter

    override fun start() {
        setUpRecyclers()
        observeStories()
        viewModel.getStories()
        viewModel.getPosts()
    }

    private fun setUpRecyclers(){
        binding.storyRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.storyRecycler.adapter = storyAdapter
        binding.storyRecycler.isNestedScrollingEnabled = false

        binding.postRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.postRecycler.adapter = postAdapter
    }

    private fun observeStories(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stories.collectLatest { stories ->
                d("FeedFragment", "Received ${stories.size} stories from ViewModel")
                if(stories.isNotEmpty()) {
                    storyAdapter.submitList(stories)
                }else {
                    d("FeedFragment", "Story list is empty")
                    e("FeedFragment", "Story list is empty")
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.posts.collectLatest { posts ->
                if(posts.isNotEmpty()) {
                    postAdapter.submitList(posts)
                }else {
                    d("FeedFragment", "Story list is empty")
                    e("FeedFragment", "Story list is empty")
                }
            }
        }
    }
}