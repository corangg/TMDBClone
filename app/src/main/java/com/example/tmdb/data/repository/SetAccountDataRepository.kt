package com.example.tmdb.data.repository

import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.model.account.CreateSessionBody
import com.example.tmdb.data.model.account.ValidateTokenBody
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import kotlinx.coroutines.launch

class SetAccountDataRepository {
    var accoutId  = -1
    var sessionId = ""

    suspend fun signIn(id : String, password : String) : String?{
        val token = TMDBRetrofit.createToken()
        token?.let {
            val body = ValidateTokenBody(username = id, password = password, request_token = it.request_token)
            val response = TMDBRetrofit.getRequestToken(body)
            if(response !=null){
                TMDBRetrofit.fetchSession(CreateSessionBody(it.request_token))?.let {
                    sessionId = it.session_id
                    return it.session_id
                }
            }
            else{
                return null
            }
        }
        return null
    }
}