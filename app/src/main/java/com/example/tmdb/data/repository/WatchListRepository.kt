package com.example.tmdb.data.repository

import android.content.Context
import com.example.tmdb.R
import com.example.tmdb.data.model.watchlist.WatchListBody
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WatchListRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val setAccountDataRepository: SetAccountDataRepository) {

    suspend fun addWatchList(check: Boolean, movieId: Int):Boolean?{
        val body = WatchListBody(
            context.getString(R.string.movie),
            movieId,
            !check
        )
        TMDBRetrofit.addWatchList(setAccountDataRepository.accountId, body)?.let {
            return check
        }
        return null
    }
}