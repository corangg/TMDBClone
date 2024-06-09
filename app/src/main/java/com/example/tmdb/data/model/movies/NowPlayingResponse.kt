package com.example.tmdb.data.model.movies

import com.example.tmdb.data.model.Result

data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)