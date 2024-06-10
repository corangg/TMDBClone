package com.example.tmdb.presentation.ui.activity

import com.example.img_decorat.ui.base.BaseActivity
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivitySeeAllActorBinding
import com.example.tmdb.presentation.ui.adapter.SeeAllActorAdapter
import com.example.tmdb.presentation.ui.adapter.SeeAllMovieActorAdapter
import com.example.tmdb.presentation.viewmodel.SeeAllActorViewmodel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.StartActivityUtil.startDetailActorInfoActivity
import com.example.tmdb.util.Util.getMovieID
import com.example.tmdb.util.Util.moreData
import com.example.tmdb.util.Util.setupLinearAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllActorActivity : BaseActivity<ActivitySeeAllActorBinding, SeeAllActorViewmodel>(),
    ItemClickInterface {

    private lateinit var seeAllActorAdapter: SeeAllActorAdapter
    private lateinit var seeAllMovieActorAdapter: SeeAllMovieActorAdapter

    private var type = ""
    private var id = -1
    private var firstPage = true

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
            seeAllActorAdapter = SeeAllActorAdapter(it.toMutableList(), this)
            firstPage = setupLinearAdapter(
                binding.actorRecycler,
                this,
                it,
                firstPage,
                seeAllActorAdapter,
                { adapter, data -> (adapter as SeeAllActorAdapter).addData(data) }
            )
        }
        viewModel.creditList.observe(this) {
            seeAllMovieActorAdapter = SeeAllMovieActorAdapter(it.toMutableList(), this)
            firstPage = setupLinearAdapter(
                binding.actorRecycler,
                this,
                it,
                firstPage,
                seeAllMovieActorAdapter,
                { adapter, data -> (adapter as SeeAllMovieActorAdapter).addData(data) }
            )
        }
        viewModel.actorId.observe(this) {
            startDetailActorInfoActivity(this, it)
        }
    }

    private fun getData() {
        val data = intent.getStringExtra(getString(R.string.seeAllActor))
        id = getMovieID(intent, this)
        data?.let {
            type = it
            viewModel.getData(type, id)
        }
    }
}