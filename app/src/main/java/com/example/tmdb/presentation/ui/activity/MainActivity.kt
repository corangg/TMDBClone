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
import com.example.tmdb.util.StartActivityUtil.startDetailActorInfoActivity
import com.example.tmdb.util.StartActivityUtil.startDetailMovieInfoActivity
import com.example.tmdb.util.StartActivityUtil.startLoginActivity
import com.example.tmdb.util.StartActivityUtil.startSeeAllActorActivity
import com.example.tmdb.util.StartActivityUtil.startSeeAllMovieActivity
import com.example.tmdb.util.TMDBUrl
import com.example.tmdb.util.Util.openInternetPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private val fragments =
        arrayOf(MoviesFragment(), CelebritiesFragment(), SearchFragment(), ProfileFragment())

    override fun layoutResId() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        intent.getStringExtra(getString(R.string.sessionID))?.let {
            viewModel.setPorfileData(it)
        }
    }

    override fun setObserve() {
        viewModel.selectNavigationItem.observe(this) {
            replaceFragment(binding.fragmentMain.id, fragments[it])
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
            replaceFragment(binding.fragmentMain.id, SavedFragment(), true)
        }

        viewModel.startLanguageFragment.observe(this) {
            replaceFragment(binding.fragmentMain.id, LanguageFragment(), true)
        }

        viewModel.startContactFragment.observe(this) {
            replaceFragment(binding.fragmentMain.id, ContactFragment(), true)
        }

        viewModel.startAboutFragment.observe(this) {
            replaceFragment(binding.fragmentMain.id, AboutFragment(), true)
        }

        viewModel.startCheckLogOutFragment.observe(this) {
            replaceFragment(binding.fragmentMain.id, LogoutCheckFragment(), true)
        }

        viewModel.connectionIC.observe(this) {
            openInternetPage(this, TMDBUrl.connectionICArray[it])
        }
    }
}