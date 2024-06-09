package com.example.tmdb.ui.fragment

import android.os.Handler
import android.os.Looper
import com.example.img_decorat.ui.base.BaseFragment
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentMoviesBinding
import com.example.tmdb.ui.UIHelper
import com.example.tmdb.ui.adapter.MovieAdapter
import com.example.tmdb.ui.viewmodel.MainViewModel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MainViewModel>(), ItemClickInterface {
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var uiHelper: UIHelper

    private lateinit var moviesAdapter: MovieAdapter
    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var upComingAdapter: MovieAdapter

    override fun layoutResId() = R.layout.fragment_movies
    override fun getViewModelClass() = MainViewModel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel

        uiHelper = UIHelper()
        viewModel.setMoviesList()
        setObserve()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onMovieItemClick(id: Int) {
        viewModel.startMovieActivity(id)
    }

    private fun setObserve() {
        viewModel.liveMoviesList.observe(viewLifecycleOwner) {
            moviesAdapter = MovieAdapter(it, 0, this)
            uiHelper.setMoviesAdapter(handler, binding.viewPager, binding.indicator, moviesAdapter)
        }

        viewModel.liveMoviesNowPlayingList.observe(viewLifecycleOwner) {
            nowPlayingAdapter = MovieAdapter(it, 1, this)
            Util.setLinearAdapter(
                binding.moviesNowPlayingRecycler,
                requireContext(),
                1,
                nowPlayingAdapter
            )
        }

        viewModel.liveMoviesPopularList.observe(viewLifecycleOwner) {
            popularAdapter = MovieAdapter(it, 2, this)
            Util.setLinearAdapter(
                binding.moviesPopularRecycler,
                requireContext(),
                1,
                popularAdapter
            )
        }

        viewModel.liveMoviesTopRatedList.observe(viewLifecycleOwner) {
            topRatedAdapter = MovieAdapter(it, 3, this)
            Util.setGridAdapter(
                binding.moviesTopRatedRecycler,
                requireContext(),
                1,
                4,
                topRatedAdapter
            )
        }

        viewModel.liveMoviesUpComingList.observe(viewLifecycleOwner) {
            upComingAdapter = MovieAdapter(it, 1, this)
            Util.setLinearAdapter(
                binding.moviesUpcomingRecycler,
                requireContext(),
                1,
                upComingAdapter
            )
        }
    }
}