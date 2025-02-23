package com.example.myapplication.module

import com.example.myapplication.data.remote.repository.PostRepository
import com.example.myapplication.data.remote.ProfileService
import com.example.myapplication.data.remote.repository.StoryRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePostRepository(api: ProfileService): PostRepository {
        return PostRepository(api)
    }

    @Provides
    @Singleton
    fun provideStoryRepository(api: ProfileService): StoryRepository {
        return StoryRepository(api)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(Json{ignoreUnknownKeys = true}
                .asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

}