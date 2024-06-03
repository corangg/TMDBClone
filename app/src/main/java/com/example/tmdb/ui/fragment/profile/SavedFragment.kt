package com.example.tmdb.ui.fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentSavedBinding
import com.example.tmdb.ui.adapter.SeeAllMovieAdapter
import com.example.tmdb.ui.viewmodel.MainViewModel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment(), ItemClickInterface {
    private val viewmodel : MainViewModel by activityViewModels()
    private lateinit var binding : FragmentSavedBinding
    private lateinit var  seeAllMovieAdapter: SeeAllMovieAdapter
    var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel

        viewmodel.getMySavedList()
        Util.moreData(binding.scrollview){viewmodel.getMySavedList()}
        setObserve()
        return binding.root
    }
    private fun setObserve(){
        viewmodel.savedList.observe(viewLifecycleOwner){
            if(page ==1){
                seeAllMovieAdapter= SeeAllMovieAdapter(it.toMutableList(), this)
                Util.setGridAdapter(binding.savedRecycler, requireContext(),0,2, seeAllMovieAdapter )
                page += 1
            }else{
                seeAllMovieAdapter.addData(it)
            }
        }
    }
}