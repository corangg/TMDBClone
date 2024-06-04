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
import com.example.tmdb.databinding.FragmentGiveRatingBinding
import com.example.tmdb.databinding.FragmentSavedBinding
import com.example.tmdb.ui.viewmodel.DetailMovieViewmodel
import com.example.tmdb.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GiveRatingFragment : Fragment() {
    private val viewmodel : DetailMovieViewmodel by activityViewModels()
    private lateinit var binding : FragmentGiveRatingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_give_rating, container, false)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel

        setObserve()
        return binding.root
    }
    private fun setObserve(){
        viewmodel.finishedGiveRatingFragment.observe(viewLifecycleOwner){
            if(it){
                parentFragmentManager.beginTransaction().remove(this).commit()
            }
        }
    }
}