package com.example.tmdb.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb.R
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.databinding.ActivitySeeAllActorBinding
import com.example.tmdb.databinding.ActivitySeeAllBinding
import com.example.tmdb.ui.adapter.SeeAllActorAdapter
import com.example.tmdb.ui.viewmodel.SeeAllActorViewmodel
import com.example.tmdb.ui.viewmodel.SeeAllMoviesViewmodel
import com.example.tmdb.util.ItemClickInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllActorActivity : AppCompatActivity(),
    ItemClickInterface {

    lateinit private var binding : ActivitySeeAllActorBinding
    private val viewmodel : SeeAllActorViewmodel by viewModels()
    private lateinit var seeAllActorAdapter: SeeAllActorAdapter

    var type = ""
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_all_actor)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel
        getData()
        moreData()
        setObserve()
    }

    private fun getData(){
        val data = intent.getStringExtra("type")
        data?.let {
            type = it
            viewmodel.getData(type)
        }
    }

    override fun onActorItemClick(id: Int) {
        viewmodel.startMovieActivity(id)
    }

    private fun setObserve(){
        viewmodel.actorList.observe(this){
            if(page ==1){
                setActorAdapter(it)
                page += 1
            }else{
                seeAllActorAdapter.addData(it)
            }
        }
        viewmodel.actorId.observe(this){
            startDetailActorInfoActivity(it)
        }
    }

    private fun setActorAdapter(list: List<CelebritiesResult>){
        binding.actorRecycler.layoutManager = LinearLayoutManager(this)
        seeAllActorAdapter = SeeAllActorAdapter(list.toMutableList(), this)
        binding.actorRecycler.adapter = seeAllActorAdapter
    }

    private fun moreData(){
        binding.scrollview.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!v.canScrollVertically(1)) {
                viewmodel.getData(type)
            }
        }
    }

    private fun startDetailActorInfoActivity(id: Int){
        val intent = Intent(this,DetailActorInfoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}