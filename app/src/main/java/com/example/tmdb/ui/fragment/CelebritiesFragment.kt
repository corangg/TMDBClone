package com.example.tmdb.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdb.R
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.databinding.FragmentCelebritiesBinding
import com.example.tmdb.ui.adapter.CelebritiesPopularAdapter
import com.example.tmdb.ui.adapter.CelebritiesTrendingAdapter
import com.example.tmdb.ui.viewmodel.MainViewModel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebritiesFragment : Fragment(), ItemClickInterface{
    private lateinit var binding: FragmentCelebritiesBinding

    private val viewModel : MainViewModel by activityViewModels()

    private lateinit var popularAdapter: CelebritiesPopularAdapter
    private lateinit var trendingAdapter: CelebritiesTrendingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_celebrities,container,false)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        setObserve()
        return binding.root
    }

    override fun onActorItemClick(id: Int) {
        viewModel.startActorActivity(id)
    }

    private fun setObserve() {
        viewModel.liveCelebritiesPopularList.observe(viewLifecycleOwner) {
            popularAdapter = CelebritiesPopularAdapter(it, this)
            Util.setGridAdapter(binding.celebritiesPopularRecycler, requireContext(), 1, 2, popularAdapter)
        }

        viewModel.liveCelebritiesTrendingList.observe(viewLifecycleOwner) {
            trendingAdapter = CelebritiesTrendingAdapter(it, this)
            Util.setGridAdapter(binding.celebritiesTrendingRecycler, requireContext(), 1, 4, trendingAdapter)
        }
    }
}