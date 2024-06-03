package com.example.tmdb.data.source.remot.retrofit

import com.example.tmdb.data.model.CreateTokenResponse
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.credit.CreditResponse
import com.example.tmdb.data.model.detailmovie.DetailsMovieResponse
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.data.model.detailactor.ActorCast
import com.example.tmdb.data.model.detailactor.DetailActorResponse
import com.example.tmdb.data.model.video.VideoResponse
import com.example.tmdb.data.source.remot.apiinterface.MoviesInterface
import com.example.tmdb.data.test.AccountDetailsResponse
import com.example.tmdb.data.test.CreateSessionBody
import com.example.tmdb.data.test.SessionResponse
import com.example.tmdb.data.test.ValidateTokenBody
import com.example.tmdb.data.test.ValidateTokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDBRetrofit {
    val authHeader = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0OTUwZmEyYTRiYTVmMGQ5NWZmYzhiZGFkNDc4NWVmYSIsInN1YiI6IjY2NTZkYjY5YmIxNDRjOGQ3N2E2ZGE1YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-8qeYfk8z7lvaeb2TyW7WYBJZ2JZ33QFDgN0E_5zZgU"
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val tmdbApi = retrofit.create(MoviesInterface::class.java)

    suspend fun createToken():CreateTokenResponse? {
        return withContext(Dispatchers.IO) {
            val call = tmdbApi.createRequestToken(authHeader)
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

    suspend fun getRequestToken(body: ValidateTokenBody):ValidateTokenResponse?{
        return withContext(Dispatchers.IO) {
            /*val call = tmdbApi.validateRequestToken(authHeader,body)
            try {
                val response = call.execute()
                if(response.isSuccessful){
                    response.body()
                }else{
                    null
                }
            }catch (e: Exception){
                null
            }*/
            try {
                tmdbApi.validateRequestToken(authHeader, body)
            } catch (e: HttpException) {
                null
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchSession(token: CreateSessionBody):SessionResponse?{
        return withContext(Dispatchers.IO) {
            try {
                tmdbApi.createSession(authHeader, token)
            } catch (e: HttpException) {
                null
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun getAccountId(id: String):AccountDetailsResponse?{
        return withContext(Dispatchers.IO) {
            try {
                tmdbApi.getAccountDetails(authHeader,id)
            } catch (e: HttpException) {
                null
            } catch (e: Exception) {
                null
            }
        }
    }

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

    suspend fun fetchDetailMovies(id : Int): DetailsMovieResponse?{
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

    suspend fun fetchCreditMovies(id : Int): CreditResponse?{
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

    suspend fun fetchVideoMovies(id : Int): VideoResponse?{
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

    suspend fun fetchSimilarMovies(id : Int, page: Int = 1): List<Result>?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getSimilarMovies(authHeader,id,"en-US", page)
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

    suspend fun fetchPopularCelebrities(page: Int = 1): List<CelebritiesResult>?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getPopularCelebrities(authHeader,"en-US", page)
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

    suspend fun fetchTrendingCelebrities(page: Int = 1): List<com.example.tmdb.data.model.celebrities.CelebritiesResult>?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getTrendingCelebrities(authHeader,"en-US", page)
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

    suspend fun fetchDetailActor(id : Int): DetailActorResponse?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getDetailActors(authHeader,id,"en-US")
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

    suspend fun fetchActorCredit(id : Int): List<ActorCast>?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getActorsCredits(authHeader,id,"en-US")
            try {
                val response = call.execute()
                if (response.isSuccessful) {
                    response.body()?.cast
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun fetchSearchMovie(keyword : String, page: Int = 1): List<Result>?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getSearchMovie(authHeader,"en-US", keyword,page)
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

    suspend fun fetchSearchActor(keyword : String, page: Int = 1): List<CelebritiesResult>?{
        return withContext(Dispatchers.IO){
            val call = tmdbApi.getSearchActor(authHeader,"en-US", keyword,page)
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



}