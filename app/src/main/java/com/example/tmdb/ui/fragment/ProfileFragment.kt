package com.example.tmdb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentProfileBinding
import com.example.tmdb.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        viewModel.setMoviesList()
        setObserve()
        return binding.root
    }

    private fun setObserve() {
        viewModel.setProfile.observe(viewLifecycleOwner) {
            binding.layerProfile.visibility = View.VISIBLE
            Glide.with(binding.root).load(R.drawable.ic_log_out).into(binding.imgLog)
        }
    }
}