package com.example.tmdb.util

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.example.tmdb.ui.activity.FullImageActivity

object Util {

    fun setImage(uri: String, root : View, view : ImageView){
        val imageUrl ="https://image.tmdb.org/t/p/w500" + uri
        Glide.with(root).load(imageUrl).into(view)
    }

}