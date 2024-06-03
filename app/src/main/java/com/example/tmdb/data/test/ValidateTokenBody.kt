package com.example.tmdb.data.test

data class ValidateTokenBody(
    val username: String,
    val password: String,
    val request_token: String
)

data class ValidateTokenResponse(
    val success: Boolean,
    val expires_at: String,
    val request_token: String
)