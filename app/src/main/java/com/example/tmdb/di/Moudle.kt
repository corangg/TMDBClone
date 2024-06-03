package com.example.tmdb.di

import android.content.Context
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.source.local.IDDB
import com.example.tmdb.data.source.local.IDDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideIDDB(@ApplicationContext context: Context): IDDB {
        return IDDB.getDatabase(context)
    }

    @Provides
    fun provideIDDao(idDB: IDDB): IDDao {
        return idDB.idDao()
    }
}