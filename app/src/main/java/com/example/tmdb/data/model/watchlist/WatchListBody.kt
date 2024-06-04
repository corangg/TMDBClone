package com.example.tmdb.data.model.watchlist

data class WatchListBody (
    val media_type: String,
    val media_id: Int,
    val watchlist: Boolean
)