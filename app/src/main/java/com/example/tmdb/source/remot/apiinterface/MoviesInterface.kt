package com.example.tmdb.source.remot.apiinterface

import com.example.tmdb.model.MoviesResponse
import com.example.tmdb.model.NowPlayingResponse
import com.example.tmdb.model.PopularResponse
import com.example.tmdb.model.TopRatedResponse
import com.example.tmdb.model.UpcomingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesInterface {
    @GET("trending/movie/day")
    fun getMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<NowPlayingResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PopularResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TopRatedResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<UpcomingResponse>


}