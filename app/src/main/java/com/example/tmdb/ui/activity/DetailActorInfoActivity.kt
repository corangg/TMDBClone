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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActorInfoActivity : AppCompatActivity(), ItemClickInterface {
    private lateinit var binding : ActivityDetailActInfoBinding
    private val viewmodel : DetailActorViewmodel by viewModels()
    private lateinit var creditMovieAdapter: CreditMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_act_info)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel

        setMovie()
        setObserve()
    }

    override fun onMovieItemClick(id: Int) {
        viewmodel.startMovieActivity(id)
    }

    private fun setMovie(){
        val id = intent.getIntExtra("id",-1)
        if(id != -1){
            viewmodel.getActData(id)
        }
    }
    private fun setObserve(){
        viewmodel.profile.observe(this){
            Util.setImage(it, binding.root, binding.imgProfile)
        }
        viewmodel.fullImage.observe(this){
            startFullImageActivity(it)
        }
        viewmodel.creditList.observe(this){
            setCreditMovieAdapter(it)
        }

        viewmodel.movieId.observe(this){
            startMovieActivity(it)
        }

        viewmodel.startSeeAllMovieActivity.observe(this){
            startSeeALlMovieActivity(it)
        }
    }

    private fun startFullImageActivity(img : String){
        val intent = Intent(this, FullImageActivity::class.java)
        intent.putExtra("img",img)
        startActivity(intent)
    }

    private fun setCreditMovieAdapter(list : List<ActorCast>){
        binding.recyclerMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        creditMovieAdapter = CreditMovieAdapter(list,this)
        binding.recyclerMovie.adapter = creditMovieAdapter
    }

    private fun startMovieActivity(id: Int){
        val intent = Intent(this,DetailMovieInfoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun startSeeALlMovieActivity(type : String){
        val intent = Intent(this, SeeAllMoviesActivity::class.java)
        intent.putExtra("type",type)
        startActivity(intent)
    }
}