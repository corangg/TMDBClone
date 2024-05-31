package com.example.tmdb.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityDetailActInfoBinding
import com.example.tmdb.ui.viewmodel.DetailActViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActorInfoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailActInfoBinding
    private val viewmodel : DetailActViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_act_info)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel

        setMovie()
        setObserve()
    }

    private fun setMovie(){
        val id = intent.getIntExtra("id",-1)
        if(id != -1){
            viewmodel.getActData(id)
        }
    }
    private fun setObserve(){}
}