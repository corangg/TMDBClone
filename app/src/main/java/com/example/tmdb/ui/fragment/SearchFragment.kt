package com.example.tmdb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentMoviesBinding
import com.example.tmdb.databinding.FragmentSearchBinding
import com.example.tmdb.ui.viewmodel.MainViewModel
import com.example.tmdb.util.ItemClickInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), ItemClickInterface {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentSearchBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,container,false)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        setObserve()
        return binding.root
    }

    private fun setObserve(){

    }

}