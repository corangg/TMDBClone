package com.example.tmdb.data.model

data class CreateTokenResponse(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)