package com.example.tmdb.ui

import android.os.Handler
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.tmdb.data.model.Result
import com.example.tmdb.ui.adapter.MovieAdapter
import com.google.android.material.progressindicator.BaseProgressIndicator
import me.relex.circleindicator.CircleIndicator3

class UIHelper {

    fun setMoviesAdapter(
        handler : Handler,
        viewPager: ViewPager2,
        indicator: CircleIndicator3,
        movieAdapter: MovieAdapter){
        val slideRunnable = object : Runnable {
            override fun run() {
                val nextItem = (viewPager.currentItem + 1) % viewPager.adapter!!.itemCount
                viewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 5000)
            }
        }
        viewPager.adapter = movieAdapter
        indicator.setViewPager(viewPager)
        handler.postDelayed(slideRunnable, 5000)
    }
}