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
import com.example.tmdb.data.model.detailactor.ActorCast
import com.example.tmdb.ui.adapter.SeeAllCreditAdapter
import com.example.tmdb.ui.adapter.SeeAllMovieAdapter
import com.example.tmdb.ui.viewmodel.SeeAllMoviesViewmodel
import com.example.tmdb.util.ItemClickInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllMoviesActivity : AppCompatActivity(),
    ItemClickInterface{

    lateinit private var binding : ActivitySeeAllBinding
    private val viewmodel : SeeAllMoviesViewmodel by viewModels()
    lateinit private var seeAllMovieAdapter  : SeeAllMovieAdapter
    lateinit private var seeAllCreditAdapter  : SeeAllCreditAdapter

    var type = ""
    var id = -1
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
        viewmodel.startMovieActivity(id)
    }

    private fun setObserve(){
        viewmodel.movieList.observe(this){
            if(page ==1){
                setMovieAdapter(it)
                page += 1
            }else{
                seeAllMovieAdapter.addData(it)
            }
        }
        viewmodel.creditMovieList.observe(this){
            setCreditAdapter(it)
        }
        viewmodel.movieId.observe(this){
            startDetailMovieInfoActivity(it)
        }
    }

    private fun getData(){
        val data = intent.getStringExtra("type")
        id = intent.getIntExtra("id",-1)
        data?.let {
            type = it
            viewmodel.getData(type, id)
        }
    }

    private fun setMovieAdapter(list: List<Result>){
        binding.movieRecycler.layoutManager = GridLayoutManager(this,2)
        seeAllMovieAdapter = SeeAllMovieAdapter(list.toMutableList(), this)
        binding.movieRecycler.adapter = seeAllMovieAdapter
    }

    private fun setCreditAdapter(list: List<ActorCast>){
        binding.movieRecycler.layoutManager = GridLayoutManager(this,2)
        seeAllCreditAdapter = SeeAllCreditAdapter(list, this)
        binding.movieRecycler.adapter = seeAllCreditAdapter
    }

    private fun moreData(){
        binding.scrollview.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!v.canScrollVertically(1)) {
                viewmodel.getData(type, id)
            }
        }
    }

    private fun startDetailMovieInfoActivity(id: Int){
        val intent = Intent(this,DetailMovieInfoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}