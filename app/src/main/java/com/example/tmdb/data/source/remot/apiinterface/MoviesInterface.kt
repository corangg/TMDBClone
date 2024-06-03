package com.example.tmdb.data.source.remot.apiinterface

import com.example.tmdb.data.model.CreateTokenResponse
import com.example.tmdb.data.model.search.SearchMovieResponse
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
import com.example.tmdb.data.model.detailactor.ActorCreditResponse
import com.example.tmdb.data.model.detailactor.DetailActorResponse
import com.example.tmdb.data.model.search.SearchActorResponse
import com.example.tmdb.data.model.video.VideoResponse
import com.example.tmdb.data.test.AccountDetailsResponse
import com.example.tmdb.data.test.CreateSessionBody
import com.example.tmdb.data.test.SessionResponse
import com.example.tmdb.data.test.ValidateTokenBody
import com.example.tmdb.data.test.ValidateTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesInterface {
    @GET("authentication/token/new")
    fun createRequestToken(@Header("Authorization") authHeader: String,): Call<CreateTokenResponse>

    @POST("authentication/token/validate_with_login")
    suspend fun validateRequestToken(
        @Header("Authorization") authHeader: String,
        @Body body: ValidateTokenBody
    ): ValidateTokenResponse//Call<ValidateTokenResponse>

    @POST("authentication/session/new")
    suspend fun createSession(
        @Header("Authorization") authHeader: String,
        @Body body: CreateSessionBody
    ): SessionResponse

    @GET("account")
    suspend fun getAccountDetails(
        @Header("Authorization") authHeader: String,
        @Query("session_id") sessionId: String
    ): AccountDetailsResponse

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

    @GET("movie/{movie_id}")
    fun getDetailMovies(
        @Header("Authorization") authHeader: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
    ): Call<DetailsMovieResponse>

    @GET("movie/{movie_id}/credits")
    fun getCreditMovies(
        @Header("Authorization") authHeader: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
    ): Call<CreditResponse>

    @GET("movie/{movie_id}/videos")
    fun getVideoMovies(
        @Header("Authorization") authHeader: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
    ): Call<VideoResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Header("Authorization") authHeader: String,
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<SimilarResponse>

    @GET("person/popular")
    fun getPopularCelebrities(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<CelebritiesPopularResponse>

    @GET("trending/person/day")
    fun getTrendingCelebrities(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<CelebritiesTrendingResponse>

    @GET("person/{person_id}")
    fun getDetailActors(
        @Header("Authorization") authHeader: String,
        @Path("person_id") personId: Int,
        @Query("language") language: String,
    ): Call<DetailActorResponse>

    @GET("person/{person_id}/movie_credits")
    fun getActorsCredits(
        @Header("Authorization") authHeader: String,
        @Path("person_id") personId: Int,
        @Query("language") language: String,
    ): Call<ActorCreditResponse>

    @GET("search/movie")
    fun getSearchMovie(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<SearchMovieResponse>

    @GET("search/person")
    fun getSearchActor(
        @Header("Authorization") authHeader: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<SearchActorResponse>

}