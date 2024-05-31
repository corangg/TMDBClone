package com.example.tmdb.source.remot.retrofit

import com.example.tmdb.model.Result
import com.example.tmdb.model.credit.CreditResponse
import com.example.tmdb.model.detailmovie.DetailsMovieResponse
import com.example.tmdb.model.SimilarResponse
import com.example.tmdb.model.video.VideoResponse
import com.example.tmdb.source.remot.apiinterface.MoviesInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDBRetrofit {
    val authHeader = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0OTUwZmEyYTRiYTVmMGQ5NWZmYzhiZGFkNDc4NWVmYSIsInN1YiI6IjY2NTZkYjY5YmIxNDRjOGQ3N2E2ZGE1YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-8qeYfk8z7lvaeb2TyW7WYBJZ2JZ33QFDgN0E_5zZgU"
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val tmdbApi = retrofit.create(MoviesInterface::class.java)

    suspend fun fetchMovies():List<Result>? {

        return withContext(Dispatchers.IO) {
            val call = tmdbApi.getMovies(authHeader, "en-US", 1)
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()?.results
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchNowPlayingMovies(page : Int = 1):List<Result>? {
        return withContext(Dispatchers.IO) {
            val call = tmdbApi.getNowPlayingMovies(authHeader, "en-US", page)
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()?.results
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchPopularMovies(page : Int = 1):List<Result>?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getPopularMovies(authHeader,"en-US", page)
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()?.results
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchTopRatedMovies(page : Int = 1):List<Result>?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getTopRatedMovies(authHeader,"en-US", page)
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()?.results
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchUpcomingMovies(page : Int = 1):List<Result>?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getUpcomingMovies(authHeader,"en-US", page)
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()?.results
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchDetailMovies(id : Int):DetailsMovieResponse?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getDetailMovies(authHeader,id,"en-US")
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchCreditMovies(id : Int):CreditResponse?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getCreditMovies(authHeader,id,"en-US")
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchVideoMovies(id : Int):VideoResponse?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getVideoMovies(authHeader,id,"en-US")
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchSimilarMovies(id : Int): SimilarResponse?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getSimilarMovies(authHeader,id,"en-US", 1)
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }


}