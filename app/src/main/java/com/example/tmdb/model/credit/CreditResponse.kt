package com.example.tmdb.model.credit

data class CreditResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)