package com.example.tmdb.presentation.ui.activity

import com.example.img_decorat.ui.base.BaseActivity
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityDetailActInfoBinding
import com.example.tmdb.presentation.ui.adapter.CreditMovieAdapter
import com.example.tmdb.presentation.viewmodel.DetailActorViewmodel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import com.example.tmdb.util.Util.setLinearAdapter
import com.example.tmdb.util.Util.startDetailMovieInfoActivity
import com.example.tmdb.util.Util.startFullImageActivity
import com.example.tmdb.util.Util.startSeeAllMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActorInfoActivity :
    BaseActivity<ActivityDetailActInfoBinding, DetailActorViewmodel>(),
    ItemClickInterface {
    private lateinit var creditMovieAdapter: CreditMovieAdapter

    var actorId = -1

    override fun layoutResId(): Int = R.layout.activity_detail_act_info

    override fun getViewModelClass() = DetailActorViewmodel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        setID()
    }

    override fun onMovieItemClick(id: Int) {
        viewModel.startMovieActivity(id)
    }

    private fun setID() {
        actorId = intent.getIntExtra(getString(R.string.actorID), -1)
        if (actorId != -1) {
            viewModel.getActData(actorId)
        }
    }

    override fun setObserve() {
        viewModel.profile.observe(this) {
            Util.setImage(it, binding.root, binding.imgProfile)
        }
        viewModel.fullImage.observe(this) {
            startFullImageActivity(this, it)
        }
        viewModel.creditList.observe(this) {
            creditMovieAdapter = CreditMovieAdapter(it, this)
            setLinearAdapter(binding.recyclerMovie, this, 1, creditMovieAdapter)
        }

        viewModel.movieId.observe(this) {
            startDetailMovieInfoActivity(this, it)
        }

        viewModel.startSeeAllMovieActivity.observe(this) {
            startSeeAllMovieActivity(this, it, actorID = actorId)
        }
    }
}