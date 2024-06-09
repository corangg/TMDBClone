package com.example.tmdb.data.repository

import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import javax.inject.Singleton

@Singleton
class GetDataRepository {

    var moviesList: List<Result> = listOf()
    var moviesNowPlayingList: List<Result> = listOf()
    var moviesPopularList: List<Result> = listOf()
    var moviesTopRatedList: List<Result> = listOf()
    var moviesUpComingList: List<Result> = listOf()
    var celebritiesPopularList: List<CelebritiesResult> = listOf()
    var celebritiesTrendingList: List<CelebritiesResult> = listOf()

    suspend fun getData() {
        TMDBRetrofit.fetchMovies()?.let {
            moviesList = it
        }
        TMDBRetrofit.fetchNowPlayingMovies()?.let {
            moviesNowPlayingList = it
        }
        TMDBRetrofit.fetchPopularMovies()?.let {
            moviesPopularList = it
        }
        TMDBRetrofit.fetchTopRatedMovies()?.let {
            moviesTopRatedList = it
        }
        TMDBRetrofit.fetchUpcomingMovies()?.let {
            moviesUpComingList = it
        }
        TMDBRetrofit.fetchPopularCelebrities()?.let {
            celebritiesPopularList = it
        }
        TMDBRetrofit.fetchTrendingCelebrities()?.let {
            celebritiesTrendingList = it
        }
    }

}