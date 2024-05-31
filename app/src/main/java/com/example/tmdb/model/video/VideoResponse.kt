package com.example.tmdb.model.video

data class VideoResponse(
    val id: Int,
    val results: List<VideoResult>
)