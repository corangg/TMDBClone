package com.example.img_decorat.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment() {
    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId(), container, false)
        (binding as ViewDataBinding).lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(getViewModelClass())

        initializeUI()
        return binding.root
    }

    abstract fun getViewModelClass(): Class<VM>
    abstract fun layoutResId(): Int
    abstract fun initializeUI()

    fun fragmentClose() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun getTransaction(): FragmentTransaction {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        return transaction
    }
}