package com.example.tmdb.data.model.detailmovie


data class SimilarResponse(
    val page: Int,
    val results: List<com.example.tmdb.data.model.Result>,
    val total_pages: Int,
    val total_results: Int
)