package com.example.tmdb.ui.fragment.profile

import com.example.img_decorat.ui.base.BaseFragment
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentSavedBinding
import com.example.tmdb.ui.adapter.SeeAllMovieAdapter
import com.example.tmdb.ui.viewmodel.MainViewModel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
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
            Util.setGridAdapter(binding.savedRecycler, requireContext(), 0, 2, seeAllMovieAdapter)
        }
    }
}