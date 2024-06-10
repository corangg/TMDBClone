package com.example.tmdb.presentation.ui.activity

import com.example.img_decorat.ui.base.BaseActivity
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivitySeeAllBinding
import com.example.tmdb.presentation.ui.adapter.SeeAllCreditAdapter
import com.example.tmdb.presentation.ui.adapter.SeeAllMovieAdapter
import com.example.tmdb.presentation.viewmodel.SeeAllMoviesViewmodel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.StartActivityUtil.startDetailMovieInfoActivity
import com.example.tmdb.util.Util.getActorID
import com.example.tmdb.util.Util.getMovieID
import com.example.tmdb.util.Util.moreData
import com.example.tmdb.util.Util.setGridAdapter
import com.example.tmdb.util.Util.setupAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllMoviesActivity : BaseActivity<ActivitySeeAllBinding, SeeAllMoviesViewmodel>(),
    ItemClickInterface {

    private lateinit var seeAllMovieAdapter: SeeAllMovieAdapter
    private lateinit var seeAllCreditAdapter: SeeAllCreditAdapter

    private var type = ""
    private var id = -1
    private var actorid = -1
    private var firstPage = true

    override fun layoutResId() = R.layout.activity_see_all
    override fun getViewModelClass() = SeeAllMoviesViewmodel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        getData()
        moreData(binding.scrollview) { viewModel.getData(type, id, actorid) }
    }

    override fun onMovieItemClick(id: Int) {
        viewModel.startMovieActivity(id)
    }

    override fun setObserve() {
        viewModel.movieList.observe(this) {
            seeAllMovieAdapter = SeeAllMovieAdapter(it.toMutableList(), this)
            firstPage = setupAdapter(
                binding.movieRecycler,
                this,
                it,
                firstPage,
                seeAllMovieAdapter,
                { adapter, data -> (adapter as SeeAllMovieAdapter).addData(data) }
            )
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
        id = getMovieID(intent, this)
        actorid = getActorID(intent, this)
        data?.let {
            type = it
            viewModel.getData(type, id, actorid)
        }
    }
}