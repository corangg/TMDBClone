package com.example.tmdb.presentation.ui

import android.os.Handler
import androidx.viewpager2.widget.ViewPager2
import com.example.tmdb.presentation.ui.adapter.MovieAdapter
import me.relex.circleindicator.CircleIndicator3

class UIHelper {

    fun setMoviesAdapter(
        handler: Handler,
        viewPager: ViewPager2,
        indicator: CircleIndicator3,
        movieAdapter: MovieAdapter
    ) {
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