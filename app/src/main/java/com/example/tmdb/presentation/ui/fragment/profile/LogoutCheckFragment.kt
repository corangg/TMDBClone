package com.example.tmdb.presentation.ui.fragment.profile

import com.example.img_decorat.ui.base.BaseFragment
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentLogoutCheckBinding
import com.example.tmdb.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutCheckFragment : BaseFragment<FragmentLogoutCheckBinding, MainViewModel>() {
    override fun layoutResId() = R.layout.fragment_logout_check
    override fun getViewModelClass() = MainViewModel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        setObserve()
    }

    private fun setObserve() {
        viewModel.finishedLogoutCheckFragment.observe(viewLifecycleOwner) {
            if (it) {
                parentFragmentManager.beginTransaction().remove(this).commit()
            }
        }
    }
}