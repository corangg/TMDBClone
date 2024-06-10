package com.example.tmdb.presentation.ui

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
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