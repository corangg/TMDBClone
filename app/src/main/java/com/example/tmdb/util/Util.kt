package com.example.tmdb.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.ui.activity.DetailActorInfoActivity
import com.example.tmdb.ui.activity.DetailMovieInfoActivity
import com.example.tmdb.ui.activity.LoginActivity
import com.example.tmdb.ui.activity.MainActivity
import com.example.tmdb.ui.activity.SeeAllActorActivity
import com.example.tmdb.ui.activity.SeeAllMoviesActivity

object Util {

    fun setImage(uri: String, root : View, view : ImageView){
        val imageUrl = TMDBUrl.imageUrl + uri
        Glide.with(root).load(imageUrl).into(view)
    }

    fun openInternetPage(context: Context, url : String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

    fun startMainActivity(context: Context, sessionId: String? = null){
        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra(getString(context, R.string.sessionID), sessionId)
        context.startActivity(intent)

    }

    fun startDetailMovieInfoActivity(context: Context, id: Int, account : Boolean){
        val intent = Intent(context, DetailMovieInfoActivity::class.java)
        intent.putExtra(getString(context,R.string.movieID), id)
        intent.putExtra(getString(context,R.string.accountCheck), account)
        context.startActivity(intent)
    }

    fun startDetailActorInfoActivity(context: Context, id: Int, account : Boolean){
        val intent = Intent(context, DetailActorInfoActivity::class.java)
        intent.putExtra(getString(context,R.string.actorID), id)
        intent.putExtra(getString(context,R.string.accountCheck), account)
        context.startActivity(intent)
    }

    fun startSeeAllMovieActivity(context: Context, type: String, account : Boolean){
        val intent = Intent(context, SeeAllMoviesActivity::class.java)
        intent.putExtra(getString(context,R.string.seeAllMovie),type)
        intent.putExtra(getString(context,R.string.accountCheck), account)
        context.startActivity(intent)
    }

    fun startSeeAllActorActivity(context: Context, type: String, account : Boolean){
        val intent = Intent(context, SeeAllActorActivity::class.java)
        intent.putExtra(getString(context,R.string.seeAllActor),type)
        intent.putExtra(getString(context,R.string.accountCheck), account)
        context.startActivity(intent)
    }

    fun startLoginActivity(context: Context){
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }


    fun setLinearAdapter(
        recyclerView: RecyclerView,
        context: Context,
        type: Int = 0,
        adapter: RecyclerView.Adapter<*>){
        val linearLayoutManager = if(type == 0){
            LinearLayoutManager.VERTICAL
        }else{
            LinearLayoutManager.HORIZONTAL
        }
        recyclerView.layoutManager = LinearLayoutManager(context, linearLayoutManager,false)
        recyclerView.adapter = adapter
    }

    fun setGridAdapter(
        recyclerView: RecyclerView,
        context: Context,
        type: Int = 0,
        spanCount : Int,
        adapter: RecyclerView.Adapter<*>){
        val gridLayoutManager = if(type == 0){
            GridLayoutManager.VERTICAL
        }else{
            GridLayoutManager.HORIZONTAL
        }
        recyclerView.layoutManager = GridLayoutManager(context, spanCount, gridLayoutManager,false)
        recyclerView.adapter = adapter
    }

    fun moreData(view : ScrollView, function: () -> Unit){
        view.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!v.canScrollVertically(1)) {
                function()
            }
        }
    }

}