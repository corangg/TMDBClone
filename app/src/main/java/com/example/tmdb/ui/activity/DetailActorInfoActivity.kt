package com.example.tmdb.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.R
import com.example.tmdb.data.model.detailactor.ActorCast
import com.example.tmdb.databinding.ActivityDetailActInfoBinding
import com.example.tmdb.ui.adapter.CreditMovieAdapter
import com.example.tmdb.ui.viewmodel.DetailActorViewmodel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import com.example.tmdb.util.Util.getAccountID
import com.example.tmdb.util.Util.setLinearAdapter
import com.example.tmdb.util.Util.startDetailMovieInfoActivity
import com.example.tmdb.util.Util.startFullImageActivity
import com.example.tmdb.util.Util.startSeeAllMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActorInfoActivity : AppCompatActivity(), ItemClickInterface {
    private lateinit var binding : ActivityDetailActInfoBinding
    private val viewmodel : DetailActorViewmodel by viewModels()
    private lateinit var creditMovieAdapter: CreditMovieAdapter

    var accountID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_act_info)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel

        accountID = getAccountID(intent, this)
        setMovie()
        setObserve()
    }

    override fun onMovieItemClick(id: Int) {
        viewmodel.startMovieActivity(id)
    }

    private fun setMovie(){
        val id = intent.getIntExtra(getString(R.string.actorID),-1)
        if(id != -1){
            viewmodel.getActData(id)
        }
    }
    private fun setObserve(){
        viewmodel.profile.observe(this){
            Util.setImage(it, binding.root, binding.imgProfile)
        }
        viewmodel.fullImage.observe(this){
            startFullImageActivity(this, it)
        }
        viewmodel.creditList.observe(this){
            creditMovieAdapter = CreditMovieAdapter(it,this)
            setLinearAdapter(binding.recyclerMovie, this, 1, creditMovieAdapter)
        }

        viewmodel.movieId.observe(this){
            startDetailMovieInfoActivity(this, it, accountID)
        }

        viewmodel.startSeeAllMovieActivity.observe(this){
            startSeeAllMovieActivity(this, it, accountID)
        }
    }
}