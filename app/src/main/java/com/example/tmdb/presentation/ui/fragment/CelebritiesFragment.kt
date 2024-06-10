package com.example.tmdb.presentation.ui.fragment

import com.example.img_decorat.ui.base.BaseFragment
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentCelebritiesBinding
import com.example.tmdb.presentation.ui.adapter.CelebritiesPopularAdapter
import com.example.tmdb.presentation.ui.adapter.CelebritiesTrendingAdapter
import com.example.tmdb.presentation.viewmodel.MainViewModel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util.setGridAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebritiesFragment : BaseFragment<FragmentCelebritiesBinding, MainViewModel>(),
    ItemClickInterface {
    private lateinit var popularAdapter: CelebritiesPopularAdapter
    private lateinit var trendingAdapter: CelebritiesTrendingAdapter

    override fun layoutResId() = R.layout.fragment_celebrities
    override fun getViewModelClass() = MainViewModel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        setObserve()
    }

    override fun onActorItemClick(id: Int) {
        viewModel.startActorActivity(id)
    }

    private fun setObserve() {
        viewModel.liveCelebritiesPopularList.observe(viewLifecycleOwner) {
            popularAdapter = CelebritiesPopularAdapter(it, this)
            setGridAdapter(
                binding.celebritiesPopularRecycler,
                requireContext(),
                1,
                2,
                popularAdapter
            )
        }

        viewModel.liveCelebritiesTrendingList.observe(viewLifecycleOwner) {
            trendingAdapter = CelebritiesTrendingAdapter(it, this)
            setGridAdapter(
                binding.celebritiesTrendingRecycler,
                requireContext(),
                1,
                4,
                trendingAdapter
            )
        }
    }
}