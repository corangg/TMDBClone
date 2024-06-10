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
import com.example.tmdb.util.Util
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
        Util.moreData(binding.scrollview) { viewModel.getMorePage() }
    }

    override fun onActorItemClick(id: Int) {
        Util.startDetailActorInfoActivity(requireContext(), id)
    }

    override fun onMovieItemClick(id: Int) {
        Util.startDetailMovieInfoActivity(requireContext(), id)
    }

    private fun setObserve() {
        viewModel.searchAny.observe(viewLifecycleOwner) {
            when (it) {
                0 -> {
                    binding.btnMovies.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.logincolor
                        )
                    )
                    binding.btnActors.setBackgroundColor(Color.BLACK)
                }

                1 -> {
                    binding.btnMovies.setBackgroundColor(Color.BLACK)
                    binding.btnActors.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.logincolor
                        )
                    )
                }
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
        Util.setGridAdapter(binding.recyclerSearch, requireContext(), 0, 2, searchMovieAdapter)
    }

    private fun setSearchActorAdapter(list: List<CelebritiesResult>) {
        searchActorAdapter = SeeAllActorAdapter(list.toMutableList(), this)
        Util.setLinearAdapter(binding.recyclerSearch, requireContext(), 0, searchActorAdapter)
    }
}