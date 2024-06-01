package com.example.tmdb.data.model.search

import com.example.tmdb.data.model.Result

data class SearchMovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)