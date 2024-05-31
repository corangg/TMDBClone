package com.example.tmdb.data.model.celebrities

data class CelebritiesResult(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<com.example.tmdb.data.model.celebrities.KnownFor>,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)