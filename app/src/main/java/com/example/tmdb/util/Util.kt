package com.example.tmdb.util

import android.app.Activity
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
import com.example.tmdb.presentation.ui.activity.DetailActorInfoActivity
import com.example.tmdb.presentation.ui.activity.DetailMovieInfoActivity
import com.example.tmdb.presentation.ui.activity.FullImageActivity
import com.example.tmdb.presentation.ui.activity.LoginActivity
import com.example.tmdb.presentation.ui.activity.MainActivity
import com.example.tmdb.presentation.ui.activity.SeeAllActorActivity
import com.example.tmdb.presentation.ui.activity.SeeAllMoviesActivity
import com.example.tmdb.presentation.ui.activity.VideoPlayActivity

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

    fun <T> setupAdapter(
        recyclerView: RecyclerView,
        context: Context,
        dataList: List<T>,
        firstPage: Boolean,
        adapter: RecyclerView.Adapter<*>,
        addData: (RecyclerView.Adapter<*>, List<T>) -> Unit,
        type: Int = 0
    ): Boolean {
        return if (firstPage) {
            setLinearAdapter(recyclerView, context, type, adapter)
            false
        } else {
            addData(recyclerView.adapter!!, dataList)
            false
        }
    }


}