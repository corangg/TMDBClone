package com.example.tmdb.util

interface ItemClickInterface {
    fun onMovieItemClick(id: Int) {}

    fun onActorItemClick(id: Int) {}

    fun onVideoItemClick(key: String) {}
}