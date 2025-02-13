package com.example.myapplication.data.di

import com.example.myapplication.data.remote.ProfileService
import com.example.myapplication.presentation.home.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(service: ProfileService): UserRepository {
        return UserRepository(service)
    }
}