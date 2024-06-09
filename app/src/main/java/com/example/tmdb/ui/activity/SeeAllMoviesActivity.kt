package com.example.tmdb.ui.activity

import com.example.img_decorat.ui.base.BaseActivity
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
class SeeAllMoviesActivity : BaseActivity<ActivitySeeAllBinding, SeeAllMoviesViewmodel>(),
    ItemClickInterface {

    private lateinit var seeAllMovieAdapter: SeeAllMovieAdapter
    private lateinit var seeAllCreditAdapter: SeeAllCreditAdapter

    var type = ""
    var id = -1
    var actorid = -1
    var page = 1

    override fun layoutResId() = R.layout.activity_see_all
    override fun getViewModelClass() = SeeAllMoviesViewmodel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        getData()
        Util.moreData(binding.scrollview) { viewModel.getData(type, id, actorid) }
    }

    override fun onMovieItemClick(id: Int) {
        viewModel.startMovieActivity(id)
    }

    override fun setObserve() {
        viewModel.movieList.observe(this) {
            if (page == 1) {
                seeAllMovieAdapter = SeeAllMovieAdapter(it.toMutableList(), this)
                setGridAdapter(binding.movieRecycler, this, 0, 2, seeAllMovieAdapter)
                page += 1
            } else {
                seeAllMovieAdapter.addData(it)
            }
        }
        viewModel.creditMovieList.observe(this) {
            seeAllCreditAdapter = SeeAllCreditAdapter(it, this)
            setGridAdapter(binding.movieRecycler, this, 0, 2, seeAllCreditAdapter)
        }
        viewModel.movieId.observe(this) {
            startDetailMovieInfoActivity(this, it)
        }
    }

    private fun getData() {
        val data = intent.getStringExtra(getString(R.string.seeAllMovie))
        id = Util.getMovieID(intent, this)
        actorid = Util.getActorID(intent, this)
        data?.let {
            type = it
            viewModel.getData(type, id, actorid)
        }
    }
}