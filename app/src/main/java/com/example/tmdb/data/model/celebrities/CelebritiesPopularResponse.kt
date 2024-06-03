package com.example.tmdb.data.model.celebrities

data class CelebritiesPopularResponse(
    val page: Int,
    val results: List<CelebritiesResult>,
    val total_pages: Int,
    val total_results: Int
)