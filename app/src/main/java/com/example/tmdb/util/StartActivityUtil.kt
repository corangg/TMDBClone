package com.example.tmdb.util

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.tmdb.R
import com.example.tmdb.presentation.ui.activity.DetailActorInfoActivity
import com.example.tmdb.presentation.ui.activity.DetailMovieInfoActivity
import com.example.tmdb.presentation.ui.activity.FullImageActivity
import com.example.tmdb.presentation.ui.activity.LoginActivity
import com.example.tmdb.presentation.ui.activity.MainActivity
import com.example.tmdb.presentation.ui.activity.SeeAllActorActivity
import com.example.tmdb.presentation.ui.activity.SeeAllMoviesActivity
import com.example.tmdb.presentation.ui.activity.VideoPlayActivity

object StartActivityUtil {
    fun startMainActivity(context: Context, sessionId: String = "") {
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(ContextCompat.getString(context, R.string.sessionID), sessionId)
        context.startActivity(intent)
    }

    fun startDetailMovieInfoActivity(context: Context, id: Int) {
        val intent = Intent(context, DetailMovieInfoActivity::class.java)
        intent.putExtra(ContextCompat.getString(context, R.string.movieID), id)
        context.startActivity(intent)
    }

    fun startDetailActorInfoActivity(context: Context, id: Int) {
        val intent = Intent(context, DetailActorInfoActivity::class.java)
        intent.putExtra(ContextCompat.getString(context, R.string.actorID), id)
        context.startActivity(intent)
    }

    fun startSeeAllMovieActivity(
        context: Context,
        type: String,
        movieID: Int = -1,
        actorID: Int = -1
    ) {
        val intent = Intent(context, SeeAllMoviesActivity::class.java)
        intent.putExtra(ContextCompat.getString(context, R.string.seeAllMovie), type)
        intent.putExtra(ContextCompat.getString(context, R.string.movieID), movieID)
        intent.putExtra(ContextCompat.getString(context, R.string.actorID), actorID)
        context.startActivity(intent)
    }

    fun startSeeAllActorActivity(context: Context, type: String, movieID: Int = -1) {
        val intent = Intent(context, SeeAllActorActivity::class.java)
        intent.putExtra(ContextCompat.getString(context, R.string.seeAllActor), type)
        intent.putExtra(ContextCompat.getString(context, R.string.movieID), movieID)
        context.startActivity(intent)
    }

    fun startFullImageActivity(context: Context, img: String) {
        val intent = Intent(context, FullImageActivity::class.java)
        intent.putExtra(context.getString(R.string.imgUrl), img)
        context.startActivity(intent)
    }

    fun startVideoActivity(context: Context, key: String) {
        val intent = Intent(context, VideoPlayActivity::class.java)
        intent.putExtra(context.getString(R.string.videoKey), key)
        context.startActivity(intent)
    }

    fun startLoginActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}