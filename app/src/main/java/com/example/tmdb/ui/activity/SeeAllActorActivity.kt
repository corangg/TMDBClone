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
import com.example.tmdb.ui.adapter.SeeAllMovieActorAdapter
import com.example.tmdb.ui.viewmodel.SeeAllActorViewmodel
import com.example.tmdb.ui.viewmodel.SeeAllMoviesViewmodel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import com.example.tmdb.util.Util.getAccountID
import com.example.tmdb.util.Util.moreData
import com.example.tmdb.util.Util.startDetailActorInfoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllActorActivity : AppCompatActivity(),
    ItemClickInterface {

    lateinit private var binding : ActivitySeeAllActorBinding
    private val viewmodel : SeeAllActorViewmodel by viewModels()
    private lateinit var seeAllActorAdapter: SeeAllActorAdapter
    private lateinit var seeAllMovieActorAdapter: SeeAllMovieActorAdapter

    var accountID = -1
    var type = ""
    var page = 1
    var id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_all_actor)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewmodel
        getData()
        accountID = getAccountID(intent, this)
        moreData(binding.scrollview){viewmodel.getData(type, id)}
        setObserve()
    }

    private fun getData(){
        val data = intent.getStringExtra(getString(R.string.seeAllActor))
        id = Util.getMovieID(intent, this)
        data?.let {
            type = it
            viewmodel.getData(type, id)
        }
    }

    override fun onActorItemClick(id: Int) {
        viewmodel.startMovieActivity(id)
    }

    private fun setObserve(){
        viewmodel.actorList.observe(this){
            if(page ==1){
                seeAllActorAdapter = SeeAllActorAdapter(it.toMutableList(), this)
                Util.setLinearAdapter(binding.actorRecycler, this, 0, seeAllActorAdapter)
                page += 1
            }else{
                seeAllActorAdapter.addData(it)
            }
        }
        viewmodel.creditList.observe(this){
            if(page ==1){
                seeAllMovieActorAdapter = SeeAllMovieActorAdapter(it.toMutableList(), this)
                Util.setLinearAdapter(binding.actorRecycler, this, 0, seeAllMovieActorAdapter)
                page += 1
            }else{
                seeAllMovieActorAdapter.addData(it)
            }
        }
        viewmodel.actorId.observe(this){
            startDetailActorInfoActivity(this, it, accountID)
        }
    }
}