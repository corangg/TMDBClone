package com.example.tmdb.domain.repository

import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.account.AccountDetailsResponse

interface AccountRepository {
    suspend fun signIn(id: String, password: String): String?
    suspend fun getAccountId(sessionId: String): AccountDetailsResponse?
    suspend fun getMyWatchList(accountId: Int): List<Result>?
    fun checkWatchList(id: Int, myWatchList: List<Result>): Boolean
}
