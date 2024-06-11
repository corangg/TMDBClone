package com.example.img_decorat.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.presentation.ui.fragment.NetworkOffFragment
import com.example.tmdb.util.NetworkUtil

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId())
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(getViewModelClass())

        initializeUI()
        setObserve()
    }

    abstract fun getViewModelClass(): Class<VM>
    abstract fun layoutResId(): Int
    abstract fun initializeUI()
    abstract fun setObserve()

    protected fun replaceFragment(id: Int, fragment: Fragment, backStack: Boolean = false) {
        if (NetworkUtil.isNetworkAvailable(this)) {
            val beginFragment = supportFragmentManager.beginTransaction().replace(id, fragment)
            if (backStack) {
                beginFragment.addToBackStack(null).commit()
            } else {
                beginFragment.commit()
            }
        } else {
            supportFragmentManager.beginTransaction().replace(id, NetworkOffFragment()).commit()
        }
    }
}