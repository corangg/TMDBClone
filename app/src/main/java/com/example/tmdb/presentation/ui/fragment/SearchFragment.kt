package com.example.tmdb.presentation.ui.fragment

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.example.img_decorat.ui.base.BaseFragment
import com.example.tmdb.R
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.databinding.FragmentSearchBinding
import com.example.tmdb.presentation.ui.adapter.SeeAllActorAdapter
import com.example.tmdb.presentation.ui.adapter.SeeAllMovieAdapter
import com.example.tmdb.presentation.viewmodel.MainViewModel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.StartActivityUtil.startDetailActorInfoActivity
import com.example.tmdb.util.StartActivityUtil.startDetailMovieInfoActivity
import com.example.tmdb.util.Util.moreData
import com.example.tmdb.util.Util.setGridAdapter
import com.example.tmdb.util.Util.setLinearAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, MainViewModel>(), ItemClickInterface {
    private lateinit var searchMovieAdapter: SeeAllMovieAdapter
    private lateinit var searchActorAdapter: SeeAllActorAdapter

    override fun layoutResId() = R.layout.fragment_search
    override fun getViewModelClass() = MainViewModel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        setObserve()
        moreData(binding.scrollview) { viewModel.getMorePage() }
    }

    override fun onActorItemClick(id: Int) {
        startDetailActorInfoActivity(requireContext(), id)
    }

    override fun onMovieItemClick(id: Int) {
        startDetailMovieInfoActivity(requireContext(), id)
    }

    private fun setObserve() {
        viewModel.searchAny.observe(viewLifecycleOwner) {
            if (it) {
                binding.btnMovies.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.logincolor
                    )
                )
                binding.btnActors.setBackgroundColor(Color.BLACK)
            } else {
                binding.btnMovies.setBackgroundColor(Color.BLACK)
                binding.btnActors.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.logincolor
                    )
                )
            }
        }

        viewModel.searchMovieList.observe(viewLifecycleOwner) {
            binding.imgBack.visibility = View.GONE
            if (viewModel.page == 1) {
                setSearchMovieAdapter(it)
            } else {
                searchMovieAdapter.addData(it)
            }
        }
        viewModel.searchActorList.observe(viewLifecycleOwner) {
            binding.imgBack.visibility = View.GONE
            if (viewModel.page == 1) {
                setSearchActorAdapter(it)
            } else {
                searchActorAdapter.addData(it)
            }
        }
    }

    private fun setSearchMovieAdapter(list: List<Result>) {
        searchMovieAdapter = SeeAllMovieAdapter(list.toMutableList(), this)
        setGridAdapter(binding.recyclerSearch, requireContext(), 0, 2, searchMovieAdapter)
    }

    private fun setSearchActorAdapter(list: List<CelebritiesResult>) {
        searchActorAdapter = SeeAllActorAdapter(list.toMutableList(), this)
        setLinearAdapter(binding.recyclerSearch, requireContext(), 0, searchActorAdapter)
    }
}