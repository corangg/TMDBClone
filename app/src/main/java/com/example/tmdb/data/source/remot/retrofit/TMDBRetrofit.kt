package com.example.tmdb.data.source.remot.retrofit

import com.example.tmdb.BuildConfig
import com.example.tmdb.data.model.CreateTokenResponse
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.data.model.credit.CreditResponse
import com.example.tmdb.data.model.detailactor.ActorCast
import com.example.tmdb.data.model.detailactor.DetailActorResponse
import com.example.tmdb.data.model.detailmovie.DetailsMovieResponse
import com.example.tmdb.data.model.rating.RatingBody
import com.example.tmdb.data.model.rating.RatingResponse
import com.example.tmdb.data.model.video.VideoResponse
import com.example.tmdb.data.model.watchlist.WatchListBody
import com.example.tmdb.data.model.watchlist.WatchListResponse
import com.example.tmdb.data.source.remot.apiinterface.MoviesInterface
import com.example.tmdb.domain.model.account.AccountDetailsResponse
import com.example.tmdb.domain.model.account.CreateSessionBody
import com.example.tmdb.domain.model.account.SessionResponse
import com.example.tmdb.domain.model.account.ValidateTokenBody
import com.example.tmdb.domain.model.account.ValidateTokenResponse
import com.example.tmdb.util.TMDBUrl
import com.example.tmdb.util.Util.getApiCall
import com.example.tmdb.util.Util.postApiCall
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TMDBRetrofit {
    val authHeader = BuildConfig.TMDB_AUTH_HEADER
    val retrofit = Retrofit.Builder()
        .baseUrl(TMDBUrl.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val tmdbApi = retrofit.create(MoviesInterface::class.java)

    suspend fun createToken(): CreateTokenResponse? {
        val tag = "createToken"
        return getApiCall(tag) {
            tmdbApi.createRequestToken(authHeader)
        }
    }

    suspend fun getRequestToken(body: ValidateTokenBody): ValidateTokenResponse? {
        val tag = "getRequestToken"
        return postApiCall(tag) {
            tmdbApi.validateRequestToken(authHeader, body)
        }
    }

    suspend fun fetchSession(token: CreateSessionBody): SessionResponse? {
        val tag = "fetchSession"
        return postApiCall(tag) {
            tmdbApi.createSession(authHeader, token)
        }
    }

    suspend fun getAccountId(id: String): AccountDetailsResponse? {
        val tag = "getAccountId"
        return postApiCall(tag) {
            tmdbApi.getAccountDetails(authHeader, id)
        }
    }

    suspend fun fetchMovies(): List<Result>? {
        val tag = "fetchMovies"
        return getApiCall(tag) {
            tmdbApi.getMovies(authHeader, "en-US", 1)
        }?.results

    }

    suspend fun fetchNowPlayingMovies(page: Int = 1): List<Result>? {
        val tag = "fetchNowPlayingMovies"
        return getApiCall(tag) {
            tmdbApi.getNowPlayingMovies(authHeader, "en-US", page)
        }?.results
    }

    suspend fun fetchPopularMovies(page: Int = 1): List<Result>? {
        val tag = "fetchPopularMovies"
        return getApiCall(tag) {
            tmdbApi.getPopularMovies(authHeader, "en-US", page)
        }?.results
    }

    suspend fun fetchTopRatedMovies(page: Int = 1): List<Result>? {
        val tag = "fetchTopRatedMovies"
        return getApiCall(tag) {
            tmdbApi.getTopRatedMovies(authHeader, "en-US", page)
        }?.results
    }

    suspend fun fetchUpcomingMovies(page: Int = 1): List<Result>? {
        val tag = "fetchUpcomingMovies"
        return getApiCall(tag) {
            tmdbApi.getUpcomingMovies(authHeader, "en-US", page)
        }?.results
    }

    suspend fun fetchDetailMovies(id: Int): DetailsMovieResponse? {
        val tag = "fetchDetailMovies"
        return getApiCall(tag) {
            tmdbApi.getDetailMovies(authHeader, id, "en-US")
        }
    }

    suspend fun fetchCreditMovies(id: Int, page: Int = 1): CreditResponse? {
        val tag = "fetchCreditMovies"
        return getApiCall(tag) {
            tmdbApi.getCreditMovies(authHeader, id, "en-US", page)
        }
    }

    suspend fun fetchVideoMovies(id: Int): VideoResponse? {
        val tag = "fetchVideoMovies"
        return getApiCall(tag) {
            tmdbApi.getVideoMovies(authHeader, id, "en-US")
        }
    }

    suspend fun fetchSimilarMovies(id: Int, page: Int = 1): List<Result>? {
        val tag = "fetchSimilarMovies"
        return getApiCall(tag) {
            tmdbApi.getSimilarMovies(authHeader, id, "en-US", page)
        }?.results
    }

    suspend fun fetchPopularCelebrities(page: Int = 1): List<CelebritiesResult>? {
        val tag = "fetchPopularCelebrities"
        return getApiCall(tag) {
            tmdbApi.getPopularCelebrities(authHeader, "en-US", page)
        }?.results
    }

    suspend fun fetchTrendingCelebrities(page: Int = 1): List<CelebritiesResult>? {
        val tag = "fetchTrendingCelebrities"
        return getApiCall(tag) {
            tmdbApi.getTrendingCelebrities(authHeader, "en-US", page)
        }?.results
    }

    suspend fun fetchDetailActor(id: Int): DetailActorResponse? {
        val tag = "fetchDetailActor"
        return getApiCall(tag) {
            tmdbApi.getDetailActors(authHeader, id, "en-US")
        }
    }

    suspend fun fetchActorCredit(id: Int, page: Int = 1): List<ActorCast>? {
        val tag = "fetchActorCredit"
        return getApiCall(tag) {
            tmdbApi.getActorsCredits(authHeader, id, "en-US", page)
        }?.cast
    }

    suspend fun fetchSearchMovie(keyword: String, page: Int = 1): List<Result>? {
        val tag = "fetchSearchMovie"
        return getApiCall(tag) {
            tmdbApi.getSearchMovie(authHeader, "en-US", keyword, page)
        }?.results
    }

    suspend fun fetchSearchActor(keyword: String, page: Int = 1): List<CelebritiesResult>? {
        val tag = "fetchSearchActor"
        return getApiCall(tag) {
            tmdbApi.getSearchActor(authHeader, "en-US", keyword, page)
        }?.results
    }

    suspend fun fetchMySavedList(id: Int): List<Result>? {
        val tag = "fetchMySavedList"
        return getApiCall(tag) {
            tmdbApi.getMySavedList(authHeader, id, "en-US", "created_at.asc")
        }?.results
    }

    suspend fun addWatchList(id: Int, body: WatchListBody): WatchListResponse? {
        val tag = "addWatchList"
        return postApiCall(tag) {
            tmdbApi.addWatchList(authHeader, id, body)
        }
    }

    suspend fun giveRating(id: Int, rating: RatingBody): RatingResponse? {
        val tag = "giveRating"
        return postApiCall(tag) {
            tmdbApi.giveRating(authHeader, id, rating)
        }
    }
}