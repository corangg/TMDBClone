package com.example.tmdb.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivitySeeAllBinding
import com.example.tmdb.ui.adapter.SeeAllCreditAdapter
import com.example.tmdb.ui.adapter.SeeAllMovieAdapter
import com.example.tmdb.ui.viewmodel.SeeAllMoviesViewmodel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import com.example.tmdb.util.Util.setGridAdapter
import com.example.tmdb.util.Util.startDetailMovieInfoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllMoviesActivity : AppCompatActivity(),
    ItemClickInterface {

    private lateinit var binding: ActivitySeeAllBinding
    private val viewmodel: SeeAllMoviesViewmodel by viewModels()
    private lateinit var seeAllMovieAdapter: SeeAllMovieAdapter
    private lateinit var seeAllCreditAdapter: SeeAllCreditAdapter

    var type = ""
    var id = -1
    var actorid = -1
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_all)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel

        getData()
        Util.moreData(binding.scrollview) { viewmodel.getData(type, id, actorid) }
        setObserve()
    }

    override fun onMovieItemClick(id: Int) {
        viewmodel.startMovieActivity(id)
    }

    private fun setObserve() {
        viewmodel.movieList.observe(this) {
            if (page == 1) {
                seeAllMovieAdapter = SeeAllMovieAdapter(it.toMutableList(), this)
                setGridAdapter(binding.movieRecycler, this, 0, 2, seeAllMovieAdapter)
                page += 1
            } else {
                seeAllMovieAdapter.addData(it)
            }
        }
        viewmodel.creditMovieList.observe(this) {
            seeAllCreditAdapter = SeeAllCreditAdapter(it, this)
            setGridAdapter(binding.movieRecycler, this, 0, 2, seeAllCreditAdapter)
        }
        viewmodel.movieId.observe(this) {
            startDetailMovieInfoActivity(this, it)
        }
    }

    private fun getData() {
        val data = intent.getStringExtra(getString(R.string.seeAllMovie))
        id = Util.getMovieID(intent, this)
        actorid = Util.getActorID(intent, this)
        data?.let {
            type = it
            viewmodel.getData(type, id, actorid)
        }
    }
}