package com.example.tmdb.domain.model.account

data class AccountDetailsResponse(
    val id: Int,
    val name: String,
    val username: String,
    val include_adult: Boolean,
    val iso_639_1: String,
    val iso_3166_1: String,
    val avatar: Avatar
)

data class Avatar(
    val gravatar: Gravatar
)

data class Gravatar(
    val hash: String
)
