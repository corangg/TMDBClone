package com.example.tmdb.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentMoviesBinding
import com.example.tmdb.model.Result
import com.example.tmdb.ui.adapter.MovieAdapter
import com.example.tmdb.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(),
    MovieAdapter.OnItemClickListener{

    private val viewModel: MainViewModel by activityViewModels()
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var binding : FragmentMoviesBinding

    private lateinit var moviesAdapter: MovieAdapter
    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var upComingAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies,container,false)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        setObserve()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onItemClick(id: Int) {
        viewModel.startActivity(id)
    }


    private fun setObserve(){
        viewModel.moviesList.observe(viewLifecycleOwner){
            setMoviesAdapter(it)
        }

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

    private fun setMoviesAdapter(list: List<Result>){
        val slideRunnable = object : Runnable {
            override fun run() {
                val nextItem = (binding.viewPager.currentItem + 1) % binding.viewPager.adapter!!.itemCount
                binding.viewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 5000)
            }
        }
        moviesAdapter = MovieAdapter(list, 0 , this)
        val viewPager = binding.viewPager
        val indicator = binding.indicator
        viewPager.adapter = moviesAdapter
        indicator.setViewPager(viewPager)
        handler.postDelayed(slideRunnable, 5000)
    }

    private fun setNowPlayingAdapter(list: List<Result>){
        binding.moviesNowPlayingRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        nowPlayingAdapter = MovieAdapter(list,1,this)
        binding.moviesNowPlayingRecycler.adapter = nowPlayingAdapter
    }

    private fun setPopularAdapter(list: List<Result>){
        binding.moviesPopularRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        popularAdapter = MovieAdapter(list,2,this)
        binding.moviesPopularRecycler.adapter = popularAdapter
    }

    private fun setTopRatedAdapter(list: List<Result>){
        binding.moviesTopRatedRecycler.layoutManager = GridLayoutManager(requireContext(),4, GridLayoutManager.HORIZONTAL,false)
        topRatedAdapter = MovieAdapter(list,3,this)
        binding.moviesTopRatedRecycler.adapter = topRatedAdapter
    }

    private fun setUpcomingAdapter(list: List<Result>){
        binding.moviesUpcomingRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        upComingAdapter = MovieAdapter(list,4,this)
        binding.moviesUpcomingRecycler.adapter = upComingAdapter
    }
}