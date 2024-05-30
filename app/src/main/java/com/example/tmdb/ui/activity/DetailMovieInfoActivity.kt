package com.example.tmdb.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityDetailMovieInfoBinding
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.ui.viewmodel.DetailMovieViewmodel
import com.example.tmdb.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieInfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailMovieInfoBinding
    private val viewModel: DetailMovieViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie_info)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        setMovie()
        setObserve()
    }

    private fun setMovie(){
        val id = intent.getIntExtra("id",-1)
        if(id != -1){
            viewModel.getMovieData(id)
        }
    }
    private fun setObserve(){
        viewModel.backImg.observe(this){
            binding.imgBack
            val imageUrl ="https://image.tmdb.org/t/p/w500" + it
            Glide.with(binding.root).load(imageUrl).into(binding.imgBack)
        }
        viewModel.posterImg.observe(this){
            val imageUrl ="https://image.tmdb.org/t/p/w500" + it
            Glide.with(binding.root).load(imageUrl).into(binding.imgPoster)
        }
        viewModel.ratingPercentInt.observe(this){
            binding.circularProgressBar.progress = it.toFloat()
        }



    }
}