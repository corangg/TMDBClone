package com.example.tmdb.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.ui.viewmodel.LoginViewmodel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel: LoginViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel
    }
}