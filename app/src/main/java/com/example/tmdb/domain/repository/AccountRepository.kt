package com.example.tmdb.domain.repository

import com.example.tmdb.data.model.Result
import com.example.tmdb.domain.model.account.AccountDetailsResponse

interface AccountRepository {
    suspend fun signIn(id: String, password: String): String?
    suspend fun getAccountId(): AccountDetailsResponse?
    suspend fun getMyWatchList(): List<Result>?
    fun checkWatchList(id: Int): Boolean
}
