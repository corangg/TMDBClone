package com.example.tmdb.ui.fragment

import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.R
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.databinding.FragmentMoviesBinding
import com.example.tmdb.databinding.FragmentSearchBinding
import com.example.tmdb.ui.adapter.MovieAdapter
import com.example.tmdb.ui.adapter.SeeAllActorAdapter
import com.example.tmdb.ui.adapter.SeeAllMovieAdapter
import com.example.tmdb.ui.viewmodel.MainViewModel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), ItemClickInterface {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentSearchBinding
    private lateinit var searchMovieAdapter: SeeAllMovieAdapter
    private lateinit var searchActorAdapter: SeeAllActorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container,false)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        setObserve()
        Util.moreData(binding.scrollview){viewModel.getMorePage()}
        return binding.root
    }

    override fun onActorItemClick(id: Int) {
        Util.startDetailActorInfoActivity(requireContext(), id, viewModel.accountId)
    }

    override fun onMovieItemClick(id: Int) {
        Util.startDetailMovieInfoActivity(requireContext(), id, viewModel.accountId)
    }

    private fun setObserve(){
        viewModel.searchAny.observe(viewLifecycleOwner){
            when(it){
                0->{
                    binding.btnMovies.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.logincolor))
                    binding.btnActors.setBackgroundColor(Color.BLACK)
                }
                1->{
                    binding.btnMovies.setBackgroundColor(Color.BLACK)
                    binding.btnActors.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.logincolor))
                }
            }
        }

        viewModel.searchMovieList.observe(viewLifecycleOwner){
            binding.imgBack.visibility = View.GONE
            if (viewModel.page ==1){
                setSearchMovieAdapter(it)
            }else{
                searchMovieAdapter.addData(it)
            }
        }
        viewModel.searchActorList.observe(viewLifecycleOwner){
            binding.imgBack.visibility = View.GONE
            if (viewModel.page  ==1){
                setSearchActorAdapter(it)
            }else{
                searchActorAdapter.addData(it)
            }
        }
    }

    private fun setSearchMovieAdapter(list: List<Result>){
        searchMovieAdapter = SeeAllMovieAdapter(list.toMutableList(),this)
        Util.setGridAdapter(binding.recyclerSearch, requireContext(), 0, 2, searchMovieAdapter)
    }

    private fun setSearchActorAdapter(list: List<CelebritiesResult>){
        searchActorAdapter = SeeAllActorAdapter(list.toMutableList(),this)
        Util.setLinearAdapter(binding.recyclerSearch, requireContext(), 0, searchActorAdapter)
    }
}