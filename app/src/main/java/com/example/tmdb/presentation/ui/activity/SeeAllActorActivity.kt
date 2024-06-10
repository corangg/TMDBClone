package com.example.tmdb.presentation.ui.activity

import com.example.img_decorat.ui.base.BaseActivity
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivitySeeAllActorBinding
import com.example.tmdb.presentation.ui.adapter.SeeAllActorAdapter
import com.example.tmdb.presentation.ui.adapter.SeeAllMovieActorAdapter
import com.example.tmdb.presentation.viewmodel.SeeAllActorViewmodel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import com.example.tmdb.util.Util.moreData
import com.example.tmdb.util.Util.startDetailActorInfoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllActorActivity : BaseActivity<ActivitySeeAllActorBinding, SeeAllActorViewmodel>(),
    ItemClickInterface {

    private lateinit var seeAllActorAdapter: SeeAllActorAdapter
    private lateinit var seeAllMovieActorAdapter: SeeAllMovieActorAdapter

    var type = ""
    var page = 1
    var id = -1

    override fun layoutResId() = R.layout.activity_see_all_actor
    override fun getViewModelClass() = SeeAllActorViewmodel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        getData()
        moreData(binding.scrollview) { viewModel.getData(type, id) }
    }

    override fun onActorItemClick(id: Int) {
        viewModel.startMovieActivity(id)
    }

    override fun setObserve() {
        viewModel.actorList.observe(this) {
            if (page == 1) {
                seeAllActorAdapter = SeeAllActorAdapter(it.toMutableList(), this)
                Util.setLinearAdapter(binding.actorRecycler, this, 0, seeAllActorAdapter)
                page += 1
            } else {
                seeAllActorAdapter.addData(it)
            }
        }
        viewModel.creditList.observe(this) {
            if (page == 1) {
                seeAllMovieActorAdapter = SeeAllMovieActorAdapter(it.toMutableList(), this)
                Util.setLinearAdapter(binding.actorRecycler, this, 0, seeAllMovieActorAdapter)
                page += 1
            } else {
                seeAllMovieActorAdapter.addData(it)
            }
        }
        viewModel.actorId.observe(this) {
            startDetailActorInfoActivity(this, it)
        }
    }

    private fun getData() {
        val data = intent.getStringExtra(getString(R.string.seeAllActor))
        id = Util.getMovieID(intent, this)
        data?.let {
            type = it
            viewModel.getData(type, id)
        }
    }
}