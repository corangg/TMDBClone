package com.example.tmdb.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.util.LogUtil.logError
import com.example.tmdb.util.LogUtil.logException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException

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

    suspend fun <T> postApiCall(tag: String, call: suspend () -> T): T? {
        return withContext(Dispatchers.IO) {
            try {
                call()
            } catch (e: HttpException) {
                logException(tag, e)
                null
            } catch (e: Exception) {
                logException(tag, e)
                null
            }
        }
    }

    suspend fun <T> getApiCall(tag: String, call: suspend () -> Call<T>): T? {
        return return withContext(Dispatchers.IO) {
            try {
                val response = call().execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    logError(tag, "${response.code()} - ${response.message()}")
                    null
                }
            } catch (e: Exception) {
                logException(tag, e)
                null
            }
        }
    }

}