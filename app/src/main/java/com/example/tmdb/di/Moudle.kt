package com.example.tmdb.di

import android.content.Context
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.repository.GetLoginDataRepository
import com.example.tmdb.data.repository.WatchListRepository
import com.example.tmdb.data.repository.SetAccountDataRepository
import com.example.tmdb.data.source.local.IDDB
import com.example.tmdb.data.source.local.IDDao
import com.example.tmdb.domain.repository.AccountRepository
import com.example.tmdb.domain.usecase.CheckWatchListUseCase
import com.example.tmdb.domain.usecase.GetAccountIdUseCase
import com.example.tmdb.domain.usecase.GetMyWatchListUseCase
import com.example.tmdb.domain.usecase.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Moudle {

    @Singleton
    @Provides
    fun provideIDDB(@ApplicationContext context: Context): IDDB {
        return IDDB.getDatabase(context)
    }

    @Provides
    fun provideIDDao(idDB: IDDB): IDDao {
        return idDB.idDao()
    }

    @Provides
    @Singleton
    fun provideGetDataRepository(): GetDataRepository {
        return GetDataRepository()
    }

    @Provides
    @Singleton
    fun provideGetLoginDataRepository(idDao: IDDao): GetLoginDataRepository {
        return GetLoginDataRepository(idDao)
    }

    @Provides
    @Singleton
    fun provideSetAccountDataRepository(): SetAccountDataRepository {
        return SetAccountDataRepository()
    }

    @Provides
    @Singleton
    fun provideWatchListRepository(
        @ApplicationContext context: Context,
        setAccountDataRepository: SetAccountDataRepository
    ): WatchListRepository {
        return WatchListRepository(context, setAccountDataRepository)
    }

   /* @Provides
    @Singleton
    fun provideAccountRepository(): AccountRepository {
        return SetAccountDataRepository()
    }*/

    /*@Provides
    @Singleton
    fun provideGetAccountIdUseCase(accountRepository: AccountRepository): GetAccountIdUseCase {
        return GetAccountIdUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(accountRepository: AccountRepository): SignInUseCase {
        return SignInUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun provideGetMyWatchList(accountRepository: AccountRepository): GetMyWatchListUseCase {
        return GetMyWatchListUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun provideCheckWatchList(accountRepository: AccountRepository): CheckWatchListUseCase {
        return CheckWatchListUseCase(accountRepository)
    }*/
}