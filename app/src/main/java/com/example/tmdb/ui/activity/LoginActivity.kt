package com.example.tmdb.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityLoginBinding
import com.example.tmdb.ui.viewmodel.LoginViewmodel
import com.example.tmdb.util.TMDBUrl
import com.example.tmdb.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private val viewModel: LoginViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        setObserve()
    }

    private fun setObserve(){
        viewModel.startMainActivity.observe(this){
            Util.startMainActivity(this)
            finish()
        }
        viewModel.openSignUpPage.observe(this){
            Util.openInternetPage(this, TMDBUrl.signUpUrl)
        }
    }
}