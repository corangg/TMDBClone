package com.example.tmdb.ui.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
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
    private val viewmodel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentSavedBinding
    private lateinit var seeAllMovieAdapter: SeeAllMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel

        viewmodel.getMySavedList()
        setObserve()
        return binding.root
    }

    override fun onMovieItemClick(id: Int) {
        viewmodel.startMovieActivity(id)
    }

    private fun setObserve() {
        viewmodel.savedList.observe(viewLifecycleOwner) {
            seeAllMovieAdapter = SeeAllMovieAdapter(it.toMutableList(), this)
            Util.setGridAdapter(binding.savedRecycler, requireContext(), 0, 2, seeAllMovieAdapter)
        }
    }
}