package com.example.tmdb.data.repository

import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.account.AccountDetailsResponse
import com.example.tmdb.data.model.account.CreateSessionBody
import com.example.tmdb.data.model.account.ValidateTokenBody
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import com.example.tmdb.domain.repository.AccountRepository

class SetAccountDataRepository : AccountRepository {

    override suspend fun signIn(id: String, password: String): String? {
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
                    return it.session_id
                }
            } else {
                return null
            }
        }
        return null
    }

    override suspend fun getAccountId(sessionId: String): AccountDetailsResponse? {
        if (sessionId != "") {
            TMDBRetrofit.getAccountId(sessionId)?.let {
                return it
            }
        }
        return null
    }

    override suspend fun getMyWatchList(accountId: Int): List<Result>? {
        TMDBRetrofit.fetchMySavedList(accountId)?.let {
            return it
        }
        return null
    }

    override fun checkWatchList(id: Int, myWatchList: List<Result>): Boolean {
        val checkWatchList = myWatchList.find { it.id == id }
        return if (checkWatchList != null) {
            true
        } else {
            false
        }
    }

}
