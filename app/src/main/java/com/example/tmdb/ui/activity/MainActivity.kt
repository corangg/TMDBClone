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
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityLoginBinding
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.ui.fragment.CelebritiesFragment
import com.example.tmdb.ui.fragment.MoviesFragment
import com.example.tmdb.ui.fragment.ProfileFragment
import com.example.tmdb.ui.fragment.SearchFragment
import com.example.tmdb.ui.viewmodel.LoginViewmodel
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
                    supportFragmentManager.beginTransaction().replace(binding.fragmentMain.id, MoviesFragment()).commit()
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
            val intent = Intent(this,DetailMovieInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.startSeeAllActivity.observe(this){
            val intent = Intent(this,SeeAllActivity::class.java)
            intent.putExtra("type",it)
            startActivity(intent)
        }

    }
}