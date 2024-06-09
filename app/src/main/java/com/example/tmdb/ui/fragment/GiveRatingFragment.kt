package com.example.tmdb.ui.fragment

import com.example.img_decorat.ui.base.BaseFragment
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentGiveRatingBinding
import com.example.tmdb.ui.viewmodel.DetailMovieViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GiveRatingFragment : BaseFragment<FragmentGiveRatingBinding, DetailMovieViewmodel>() {
    override fun layoutResId() = R.layout.fragment_give_rating
    override fun getViewModelClass() = DetailMovieViewmodel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        setObserve()
    }

    private fun setObserve() {
        viewModel.finishedGiveRatingFragment.observe(viewLifecycleOwner) {
            if (it) {
                parentFragmentManager.beginTransaction().remove(this).commit()
            }
        }
    }
}