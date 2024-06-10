package com.example.tmdb.presentation.ui.fragment.profile

import com.example.img_decorat.ui.base.BaseFragment
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentSavedBinding
import com.example.tmdb.presentation.ui.adapter.SeeAllMovieAdapter
import com.example.tmdb.presentation.viewmodel.MainViewModel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util.setGridAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : BaseFragment<FragmentSavedBinding, MainViewModel>(), ItemClickInterface {
    private lateinit var seeAllMovieAdapter: SeeAllMovieAdapter

    override fun layoutResId() = R.layout.fragment_saved
    override fun getViewModelClass() = MainViewModel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        viewModel.getMySavedList()
        setObserve()
    }

    override fun onMovieItemClick(id: Int) {
        viewModel.startMovieActivity(id)
    }

    private fun setObserve() {
        viewModel.savedList.observe(viewLifecycleOwner) {
            seeAllMovieAdapter = SeeAllMovieAdapter(it.toMutableList(), this)
            setGridAdapter(binding.savedRecycler, requireContext(), 0, 2, seeAllMovieAdapter)
        }
    }
}