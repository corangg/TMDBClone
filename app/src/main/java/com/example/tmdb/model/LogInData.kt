package com.example.tmdb.model

data class RequestTokenResponse(val success: Boolean, val request_token: String)
data class SessionResponse(val success: Boolean, val session_id: String)