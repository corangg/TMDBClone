package com.example.tmdb.data.model.detailmovie

data class DetailsMovieResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: com.example.tmdb.data.model.detailmovie.BelongsToCollection,
    val budget: Int,
    val genres: List<com.example.tmdb.data.model.detailmovie.Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<com.example.tmdb.data.model.detailmovie.ProductionCompany>,
    val production_countries: List<com.example.tmdb.data.model.detailmovie.ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<com.example.tmdb.data.model.detailmovie.SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)