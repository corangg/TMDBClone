package com.example.tmdb.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

object Util {

    fun setImage(uri: String, root : View, view : ImageView){
        val imageUrl ="https://image.tmdb.org/t/p/w500" + uri
        Glide.with(root).load(imageUrl).into(view)
    }
}