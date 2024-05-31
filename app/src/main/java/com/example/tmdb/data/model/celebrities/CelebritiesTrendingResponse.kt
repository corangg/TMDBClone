package com.example.tmdb.data.model.celebrities

data class CelebritiesTrendingResponse(
    val page: Int,
    val results: List<com.example.tmdb.data.model.celebrities.CelebritiesResult>,
    val total_pages: Int,
    val total_results: Int
)