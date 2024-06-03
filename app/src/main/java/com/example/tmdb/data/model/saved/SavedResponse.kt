package com.example.tmdb.data.model.saved

import com.example.tmdb.data.model.Result

data class SavedResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)