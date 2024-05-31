package com.example.tmdb.model

import kotlin.Result


data class SimilarResponse(
    val page: Int,
    val results: List<com.example.tmdb.model.Result>,
    val total_pages: Int,
    val total_results: Int
)