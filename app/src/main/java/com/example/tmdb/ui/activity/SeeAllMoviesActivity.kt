package com.example.tmdb.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivitySeeAllBinding
import com.example.tmdb.data.model.Result
import com.example.tmdb.ui.adapter.SeeAllAdapter
import com.example.tmdb.ui.viewmodel.SeeAllViewmodel
import com.example.tmdb.util.ItemClickInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllMoviesActivity : AppCompatActivity(),
    ItemClickInterface{

    lateinit private var binding : ActivitySeeAllBinding
    private val viewmodel : SeeAllViewmodel by viewModels()
    lateinit private var seeAllAdapter  : SeeAllAdapter

    var type = ""
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_all)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel
        getData()
        moreData()
        setObserve()
    }

    override fun onMovieItemClick(id: Int) {
        startDetailMovieInfoActivity(id)
    }

    private fun setObserve(){
        viewmodel.movieList.observe(this){
            if(page ==1){
                setAdapter(it)
                page += 1
            }else{
                seeAllAdapter.addData(it)
            }
        }
    }

    private fun getData(){
        val data = intent.getStringExtra("type")
        data?.let {
            type = it
            viewmodel.getData(type)
        }
    }

    private fun setAdapter(list: List<com.example.tmdb.data.model.Result>){
        binding.movieRecycler.layoutManager = GridLayoutManager(this,2)
        seeAllAdapter = SeeAllAdapter(list.toMutableList(), this)
        binding.movieRecycler.adapter = seeAllAdapter
    }

    private fun moreData(){
        binding.scrollview.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!v.canScrollVertically(1)) {
                viewmodel.getData(type)
            }
        }
    }

    private fun startDetailMovieInfoActivity(id: Int){
        val intent = Intent(this,DetailMovieInfoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}