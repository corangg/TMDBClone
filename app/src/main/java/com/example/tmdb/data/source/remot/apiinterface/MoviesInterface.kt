package com.example.tmdb.data.source.remot.apiinterface

import com.example.tmdb.data.model.movies.MoviesResponse
import com.example.tmdb.data.model.movies.NowPlayingResponse
import com.example.tmdb.data.model.movies.PopularResponse
import com.example.tmdb.data.model.movies.TopRatedResponse
import com.example.tmdb.data.model.movies.UpcomingResponse
import com.example.tmdb.data.model.credit.CreditResponse
import com.example.tmdb.data.model.detailmovie.DetailsMovieResponse
import com.example.tmdb.data.model.detailmovie.SimilarResponse
import com.example.tmdb.data.model.celebrities.CelebritiesPopularResponse
import com.example.tmdb.data.model.celebrities.CelebritiesTrendingResponse
import com.example.tmdb.data.model.video.VideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesInterface {
    @GET("trending/movie/day")
    fun getMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<com.example.tmdb.data.model.movies.MoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<com.example.tmdb.data.model.movies.NowPlayingResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<com.example.tmdb.data.model.movies.PopularResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<com.example.tmdb.data.model.movies.TopRatedResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<com.example.tmdb.data.model.movies.UpcomingResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovies(
        @Header("Authorization") authHeader: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
    ): Call<com.example.tmdb.data.model.detailmovie.DetailsMovieResponse>

    @GET("movie/{movie_id}/credits")
    fun getCreditMovies(
        @Header("Authorization") authHeader: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
    ): Call<com.example.tmdb.data.model.credit.CreditResponse>

    @GET("movie/{movie_id}/videos")
    fun getVideoMovies(
        @Header("Authorization") authHeader: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
    ): Call<com.example.tmdb.data.model.video.VideoResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Header("Authorization") authHeader: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<com.example.tmdb.data.model.detailmovie.SimilarResponse>

    @GET("person/popular")
    fun getPopularCelebrities(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<com.example.tmdb.data.model.celebrities.CelebritiesPopularResponse>

    @GET("trending/person/day")
    fun getTrendingCelebrities(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<com.example.tmdb.data.model.celebrities.CelebritiesTrendingResponse>

}