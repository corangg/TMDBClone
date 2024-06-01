package com.example.tmdb.data.model.detailactor

data class ActorCreditResponse(
    val cast: List<ActorCast>,
    val crew: List<ActorCrew>,
    val id: Int
)