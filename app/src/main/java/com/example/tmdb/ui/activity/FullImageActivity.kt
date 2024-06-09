package com.example.tmdb.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityFullImageBinding
import com.example.tmdb.ui.viewmodel.FullImageViewmodel
import com.example.tmdb.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullImageBinding
    private val viewmodel: FullImageViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_image)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel
        ImgSet()
        setObserve()
    }

    private fun ImgSet() {
        val url = intent.getStringExtra(getString(R.string.imgUrl))
        url?.let {
            Util.setImage(it, binding.root, binding.img)
        }
    }

    private fun setObserve() {
        viewmodel.back.observe(this) {
            finish()
        }
    }
}