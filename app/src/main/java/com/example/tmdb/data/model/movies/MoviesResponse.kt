package com.example.tmdb.data.model.movies

import com.example.tmdb.data.model.Result

data class MoviesResponse(
    val page: Int,
    val results: List<com.example.tmdb.data.model.Result>,
    val total_pages: Int,
    val total_results: Int
)