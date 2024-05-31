package com.example.tmdb.data.model.video

data class VideoResponse(
    val id: Int,
    val results: List<com.example.tmdb.data.model.video.VideoResult>
)