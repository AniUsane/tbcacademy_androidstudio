package com.example.myapplication.homePage

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.ProfileService
import com.example.myapplication.User

class UserPaging(private val service: ProfileService): PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try{
            val position = params.key ?: 1
            val response = service.getUserList(position)

            LoadResult.Page(
                data = response.data,
                prevKey = if(position == 1) null else (position - 1),
                nextKey = if(response.data.isEmpty()) null else (position + 1)
                )
        }catch(e: Exception){
            LoadResult.Error(e)
        }
    }
}