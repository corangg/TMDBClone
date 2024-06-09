package com.example.tmdb.ui.activity

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
import com.example.tmdb.ui.fragment.profile.AboutFragment
import com.example.tmdb.ui.fragment.profile.ContactFragment
import com.example.tmdb.ui.fragment.profile.LanguageFragment
import com.example.tmdb.ui.fragment.profile.LogoutCheckFragment
import com.example.tmdb.ui.fragment.profile.SavedFragment
import com.example.tmdb.ui.viewmodel.MainViewModel
import com.example.tmdb.util.TMDBUrl
import com.example.tmdb.util.Util
import com.example.tmdb.util.Util.startDetailActorInfoActivity
import com.example.tmdb.util.Util.startDetailMovieInfoActivity
import com.example.tmdb.util.Util.startLoginActivity
import com.example.tmdb.util.Util.startSeeAllActorActivity
import com.example.tmdb.util.Util.startSeeAllMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        viewModel.setPorfileData()
        setObserve()
    }


    private fun setObserve() {
        viewModel.selectNavigationItem.observe(this) {
            when (it) {
                0 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentMain.id, MoviesFragment()).commit()
                }

                1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentMain.id, CelebritiesFragment()).commit()
                }

                2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentMain.id, SearchFragment()).commit()
                }

                3 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentMain.id, ProfileFragment()).commit()
                }
            }
        }

        viewModel.movieId.observe(this) {
            startDetailMovieInfoActivity(this, it)
        }

        viewModel.actorId.observe(this) {
            startDetailActorInfoActivity(this, it)
        }

        viewModel.startSeeAllMovieActivity.observe(this) {
            startSeeAllMovieActivity(this, it)
        }

        viewModel.startSeeAllActorActivity.observe(this) {
            startSeeAllActorActivity(this, it)
        }
        viewModel.startLoginActivity.observe(this) {
            startLoginActivity(this)
            finish()
        }

        viewModel.startSavedFragment.observe(this) {
            supportFragmentManager.beginTransaction().add(binding.fragmentMain.id, SavedFragment())
                .addToBackStack(null).commit()
        }

        viewModel.startLanguageFragment.observe(this) {
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentMain.id, LanguageFragment()).addToBackStack(null).commit()
        }

        viewModel.startContactFragment.observe(this) {
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentMain.id, ContactFragment()).addToBackStack(null).commit()
        }

        viewModel.startAboutFragment.observe(this) {
            supportFragmentManager.beginTransaction().add(binding.fragmentMain.id, AboutFragment())
                .addToBackStack(null).commit()
        }

        viewModel.startCheckLogOutFragment.observe(this) {
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentMain.id, LogoutCheckFragment()).addToBackStack(null).commit()
        }

        viewModel.connectionIC.observe(this) {
            when (it) {
                0 -> Util.openInternetPage(this, TMDBUrl.telegramUrl)
                1 -> Util.openInternetPage(this, TMDBUrl.instargramUrl)
                2 -> Util.openInternetPage(this, TMDBUrl.linkedInUrl)
            }
        }
    }
}