package com.example.tmdb.source.remot.apiinterface

import com.example.tmdb.model.NowPlayingResponse
import com.example.tmdb.model.PopularResponse
import com.example.tmdb.model.TopRatedResponse
import com.example.tmdb.model.UpcomingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesInterface {
    @GET("now_playing")
    fun getNowPlayingMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<NowPlayingResponse>

    @GET("popular")
    fun getPopularMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PopularResponse>

    @GET("top_rated")
    fun getTopRatedMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TopRatedResponse>

    @GET("upcoming")
    fun getUpcomingMovies(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<UpcomingResponse>

}