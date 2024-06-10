package com.example.tmdb.presentation.ui.activity

import com.example.img_decorat.ui.base.BaseActivity
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityLoginBinding
import com.example.tmdb.presentation.viewmodel.LoginViewmodel
import com.example.tmdb.util.StartActivityUtil.startMainActivity
import com.example.tmdb.util.TMDBUrl
import com.example.tmdb.util.Util.openInternetPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewmodel>() {
    override fun layoutResId() = R.layout.activity_login

    override fun getViewModelClass() = LoginViewmodel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
    }

    override fun setObserve() {
        viewModel.startMainActivity.observe(this) {
            startMainActivity(this)
            finish()
        }
        viewModel.openSignUpPage.observe(this) {
            openInternetPage(this, TMDBUrl.signUpUrl)
        }
    }
}