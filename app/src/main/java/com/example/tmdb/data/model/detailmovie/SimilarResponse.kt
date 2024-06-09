package com.example.tmdb.data.model.detailmovie

import com.example.tmdb.data.model.Result

data class SimilarResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)