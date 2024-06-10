package com.example.tmdb.presentation.ui.activity

import com.example.img_decorat.ui.base.BaseActivity
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.presentation.ui.fragment.CelebritiesFragment
import com.example.tmdb.presentation.ui.fragment.MoviesFragment
import com.example.tmdb.presentation.ui.fragment.ProfileFragment
import com.example.tmdb.presentation.ui.fragment.SearchFragment
import com.example.tmdb.presentation.ui.fragment.profile.AboutFragment
import com.example.tmdb.presentation.ui.fragment.profile.ContactFragment
import com.example.tmdb.presentation.ui.fragment.profile.LanguageFragment
import com.example.tmdb.presentation.ui.fragment.profile.LogoutCheckFragment
import com.example.tmdb.presentation.ui.fragment.profile.SavedFragment
import com.example.tmdb.presentation.viewmodel.MainViewModel
import com.example.tmdb.util.TMDBUrl
import com.example.tmdb.util.Util
import com.example.tmdb.util.Util.beginFragment
import com.example.tmdb.util.Util.startDetailActorInfoActivity
import com.example.tmdb.util.Util.startDetailMovieInfoActivity
import com.example.tmdb.util.Util.startLoginActivity
import com.example.tmdb.util.Util.startSeeAllActorActivity
import com.example.tmdb.util.Util.startSeeAllMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private val fragments = arrayOf(MoviesFragment(), CelebritiesFragment(), SearchFragment(), ProfileFragment())

    override fun layoutResId() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        viewModel.setPorfileData()
    }

    override fun setObserve() {
        viewModel.selectNavigationItem.observe(this) {
            beginFragment(supportFragmentManager, binding.fragmentMain.id, fragments[it])
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