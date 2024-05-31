package com.example.tmdb.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.ui.fragment.CelebritiesFragment
import com.example.tmdb.ui.fragment.MoviesFragment
import com.example.tmdb.ui.fragment.ProfileFragment
import com.example.tmdb.ui.fragment.SearchFragment
import com.example.tmdb.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        setObserve()
    }

    private fun setObserve(){
        viewModel.selectNavigationItem.observe(this){
            when(it){
                0->{
                    //supportFragmentManager.beginTransaction().replace(binding.fragmentMain.id, MoviesFragment()).commit()
                }
                1->{
                    supportFragmentManager.beginTransaction().replace(binding.fragmentMain.id, CelebritiesFragment()).commit()
                }
                2->{
                    supportFragmentManager.beginTransaction().replace(binding.fragmentMain.id, SearchFragment()).commit()
                }
                3->{
                    supportFragmentManager.beginTransaction().replace(binding.fragmentMain.id, ProfileFragment()).commit()
                }
            }
        }

        viewModel.movieId.observe(this){
            startDetailMovieActivity(it)
        }

        viewModel.actorId.observe(this){
            startDetailActorActivity(it)
        }

        viewModel.startSeeAllMovieActivity.observe(this){
            startSeeAllMovieActivity(it)
        }

        viewModel.startSeeAllActorActivity.observe(this){
            startSeeAllActorActivity(it)
        }
    }

    private fun startDetailMovieActivity(id : Int){
        val intent = Intent(this, DetailMovieInfoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun startDetailActorActivity(id: Int){
        val intent = Intent(this, DetailActorInfoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun startSeeAllMovieActivity(type: String){
        val intent = Intent(this,SeeAllMoviesActivity::class.java)
        intent.putExtra("type",type)
        startActivity(intent)
    }

    private fun startSeeAllActorActivity(type: String){

    }
}