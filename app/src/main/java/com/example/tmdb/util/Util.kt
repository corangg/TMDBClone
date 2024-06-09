package com.example.tmdb.util

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.tmdb.R
import com.example.tmdb.ui.activity.DetailActorInfoActivity
import com.example.tmdb.ui.activity.DetailMovieInfoActivity
import com.example.tmdb.ui.activity.FullImageActivity
import com.example.tmdb.ui.activity.LoginActivity
import com.example.tmdb.ui.activity.MainActivity
import com.example.tmdb.ui.activity.SeeAllActorActivity
import com.example.tmdb.ui.activity.SeeAllMoviesActivity
import com.example.tmdb.ui.activity.VideoPlayActivity

object Util {
    fun setImage(uri: String, root: View, view: ImageView) {
        val imageUrl = TMDBUrl.imageUrl + uri
        Glide.with(root).load(imageUrl).into(view)
    }

    fun openInternetPage(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

    fun getMovieID(intent: Intent, context: Context): Int {
        return intent.getIntExtra(context.getString(R.string.movieID), -1)
    }

    fun getActorID(intent: Intent, context: Context): Int {
        return intent.getIntExtra(context.getString(R.string.actorID), -1)
    }

    fun startMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    fun startDetailMovieInfoActivity(context: Context, id: Int) {
        val intent = Intent(context, DetailMovieInfoActivity::class.java)
        intent.putExtra(getString(context, R.string.movieID), id)
        context.startActivity(intent)
    }

    fun startDetailActorInfoActivity(context: Context, id: Int) {
        val intent = Intent(context, DetailActorInfoActivity::class.java)
        intent.putExtra(getString(context, R.string.actorID), id)
        context.startActivity(intent)
    }

    fun startSeeAllMovieActivity(
        context: Context,
        type: String,
        movieID: Int = -1,
        actorID: Int = -1
    ) {
        val intent = Intent(context, SeeAllMoviesActivity::class.java)
        intent.putExtra(getString(context, R.string.seeAllMovie), type)
        intent.putExtra(getString(context, R.string.movieID), movieID)
        intent.putExtra(getString(context, R.string.actorID), actorID)
        context.startActivity(intent)
    }

    fun startSeeAllActorActivity(context: Context, type: String, movieID: Int = -1) {
        val intent = Intent(context, SeeAllActorActivity::class.java)
        intent.putExtra(getString(context, R.string.seeAllActor), type)
        intent.putExtra(getString(context, R.string.movieID), movieID)
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

    fun setLinearAdapter(
        recyclerView: RecyclerView,
        context: Context,
        type: Int = 0,
        adapter: RecyclerView.Adapter<*>
    ) {
        val linearLayoutManager = if (type == 0) {
            LinearLayoutManager.VERTICAL
        } else {
            LinearLayoutManager.HORIZONTAL
        }
        recyclerView.layoutManager = LinearLayoutManager(context, linearLayoutManager, false)
        recyclerView.adapter = adapter
    }

    fun setGridAdapter(
        recyclerView: RecyclerView,
        context: Context,
        type: Int = 0,
        spanCount: Int,
        adapter: RecyclerView.Adapter<*>
    ) {
        val gridLayoutManager = if (type == 0) {
            GridLayoutManager.VERTICAL
        } else {
            GridLayoutManager.HORIZONTAL
        }
        recyclerView.layoutManager = GridLayoutManager(context, spanCount, gridLayoutManager, false)
        recyclerView.adapter = adapter
    }

    fun moreData(view: ScrollView, function: () -> Unit) {
        view.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!v.canScrollVertically(1)) {
                function()
            }
        }
    }

    fun imgButtonSet(drawable: Int, root: View, view: ImageButton, color: Int) {
        Glide.with(root).load(drawable).apply(RequestOptions().centerCrop()).listener(object :
            RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                return false
            }
        }).into(view)
    }

}