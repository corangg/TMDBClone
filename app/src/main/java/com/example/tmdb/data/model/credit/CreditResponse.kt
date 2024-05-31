package com.example.tmdb.data.model.credit

data class CreditResponse(
    val cast: List<com.example.tmdb.data.model.credit.Cast>,
    val crew: List<com.example.tmdb.data.model.credit.Crew>,
    val id: Int
)