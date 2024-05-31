package com.example.tmdb.di

import com.example.tmdb.data.repository.GetDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Moudle {

    @Provides
    @Singleton
    fun provideGetDataRepository(): GetDataRepository{
        return GetDataRepository()
    }
}