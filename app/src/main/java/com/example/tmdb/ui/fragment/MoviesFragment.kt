package com.example.tmdb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentMoviesBinding
import com.example.tmdb.model.Result
import com.example.tmdb.ui.adapter.MoviesNowPlayingAdapter
import com.example.tmdb.ui.adapter.MoviesPopularAdapter
import com.example.tmdb.ui.adapter.MoviesTopRatedAdapter
import com.example.tmdb.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(),
    MoviesNowPlayingAdapter.OnMoviesNowPlayingItemClickListener,
    MoviesPopularAdapter.OnMoviesPopularItemClickListener,
    MoviesTopRatedAdapter.OnMoviesTopRatedItemClickListener
{

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentMoviesBinding
    private lateinit var nowPlayingAdapter: MoviesNowPlayingAdapter
    private lateinit var popularAdapter: MoviesPopularAdapter
    private lateinit var topRatedAdapter: MoviesTopRatedAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies,container,false)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        setObserve()
        return binding.root
    }

    override fun onMoviesNowPlayingItemClick(position: Int) {
    }

    override fun onMoviesPopularItemClick(position: Int) {
    }

    override fun onMoviesTopRatedItemClick(position: Int) {
    }

    override fun onMoviesTopRatedDetailItemClick(position: Int) {
    }

    private fun setObserve(){
        viewModel.nowPlayingList.observe(viewLifecycleOwner){
            setNowPlayingAdapter(it)
        }
        viewModel.popularList.observe(viewLifecycleOwner){
            setPopularAdapter(it)
        }

        viewModel.topRatedList.observe(viewLifecycleOwner){
            setTopRatedAdapter(it)
        }

        viewModel.upComingList.observe(viewLifecycleOwner){
            setUpcomingAdapter(it)
        }
    }

    private fun setNowPlayingAdapter(list: List<Result>){
        binding.moviesNowPlayingRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        nowPlayingAdapter = MoviesNowPlayingAdapter(list, this)
        binding.moviesNowPlayingRecycler.adapter = nowPlayingAdapter
    }

    private fun setPopularAdapter(list: List<Result>){
        binding.moviesPopularRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        popularAdapter = MoviesPopularAdapter(list, this)
        binding.moviesPopularRecycler.adapter = popularAdapter
    }

    private fun setTopRatedAdapter(list: List<Result>){
        binding.moviesTopRatedRecycler.layoutManager = GridLayoutManager(requireContext(),4, GridLayoutManager.HORIZONTAL,false)
        topRatedAdapter = MoviesTopRatedAdapter(list, this)
        binding.moviesTopRatedRecycler.adapter = topRatedAdapter
    }

    private fun setUpcomingAdapter(list: List<Result>){
        binding.moviesUpcomingRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        nowPlayingAdapter = MoviesNowPlayingAdapter(list, this)
        binding.moviesUpcomingRecycler.adapter = nowPlayingAdapter
    }

}