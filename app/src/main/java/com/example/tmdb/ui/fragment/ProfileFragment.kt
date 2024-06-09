package com.example.tmdb.ui.fragment

import android.view.View
import com.bumptech.glide.Glide
import com.example.img_decorat.ui.base.BaseFragment
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentProfileBinding
import com.example.tmdb.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, MainViewModel>() {
    override fun layoutResId() = R.layout.fragment_profile
    override fun getViewModelClass() = MainViewModel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel

        setObserve()
        viewModel.setMoviesList()
    }

    private fun setObserve() {
        viewModel.setProfile.observe(viewLifecycleOwner) {
            binding.layerProfile.visibility = View.VISIBLE
            Glide.with(binding.root).load(R.drawable.ic_log_out).into(binding.imgLog)
        }
    }
}