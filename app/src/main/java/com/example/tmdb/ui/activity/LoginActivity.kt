package com.example.tmdb.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityLoginBinding
import com.example.tmdb.ui.viewmodel.LoginViewmodel
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
        viewModel.sessionId.observe(this){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id",it)
            startActivity(intent)
            finish()
        }

        viewModel.startGuest.observe(this){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}