package com.example.img_decorat.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId())
        (binding as ViewDataBinding).lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(getViewModelClass())

        initializeUI()
        setObserve()
    }

    abstract fun getViewModelClass(): Class<VM>
    abstract fun layoutResId(): Int
    abstract fun initializeUI()
    abstract fun setObserve()

}