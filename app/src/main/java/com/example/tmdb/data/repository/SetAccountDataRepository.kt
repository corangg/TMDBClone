package com.example.tmdb.data.repository

import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.account.AccountDetailsResponse
import com.example.tmdb.data.model.account.CreateSessionBody
import com.example.tmdb.data.model.account.ValidateTokenBody
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit

class SetAccountDataRepository {
    var accountId = -1
    var sessionId = ""
    var myWatchList = listOf<Result>()

    suspend fun signIn(id: String, password: String): String? {
        val token = TMDBRetrofit.createToken()
        token?.let {
            val body = ValidateTokenBody(
                username = id,
                password = password,
                request_token = it.request_token
            )
            val response = TMDBRetrofit.getRequestToken(body)
            if (response != null) {
                TMDBRetrofit.fetchSession(CreateSessionBody(it.request_token))?.let {
                    sessionId = it.session_id
                    return it.session_id
                }
            } else {
                return null
            }
        }
        return null
    }

    suspend fun getAccountId(): AccountDetailsResponse? {
        if (sessionId != "") {
            TMDBRetrofit.getAccountId(sessionId)?.let {
                accountId = it.id
                return it
            }
        }
        return null
    }

    suspend fun getMyWatchList(): List<Result>? {
        TMDBRetrofit.fetchMySavedList(accountId)?.let {
            myWatchList = it
            return it
        }
        return null
    }

    fun checkWatchList(id: Int): Boolean {
        val checkWatchList = myWatchList.find { it.id == id }
        return if (checkWatchList != null) {
            true
        } else {
            false
        }
    }
}