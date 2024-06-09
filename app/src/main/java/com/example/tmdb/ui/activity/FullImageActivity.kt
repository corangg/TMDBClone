package com.example.tmdb.ui.activity

import com.example.img_decorat.ui.base.BaseActivity
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityFullImageBinding
import com.example.tmdb.ui.viewmodel.FullImageViewmodel
import com.example.tmdb.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullImageActivity : BaseActivity<ActivityFullImageBinding, FullImageViewmodel>() {

    override fun layoutResId() = R.layout.activity_full_image
    override fun getViewModelClass() = FullImageViewmodel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        imageSet()
    }

    override fun setObserve() {
        viewModel.back.observe(this) {
            finish()
        }
    }

    private fun imageSet() {
        val url = intent.getStringExtra(getString(R.string.imgUrl))
        url?.let {
            Util.setImage(it, binding.root, binding.img)
        }
    }
}