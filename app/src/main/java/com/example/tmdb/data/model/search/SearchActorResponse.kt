package com.example.tmdb.data.model.search

import com.example.tmdb.data.model.celebrities.CelebritiesResult

data class SearchActorResponse(
    val page: Int,
    val results: List<CelebritiesResult>,
    val total_pages: Int,
    val total_results: Int
)