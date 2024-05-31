package com.example.tmdb.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityDetailMovieInfoBinding
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.ui.adapter.CastAdapter
import com.example.tmdb.ui.adapter.CompanyAdapter
import com.example.tmdb.ui.adapter.CountryAdapter
import com.example.tmdb.ui.adapter.GenreAdapter
import com.example.tmdb.ui.adapter.MovieAdapter
import com.example.tmdb.ui.adapter.VideoAdapter
import com.example.tmdb.ui.viewmodel.DetailMovieViewmodel
import com.example.tmdb.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieInfoActivity : AppCompatActivity(),
    CastAdapter.OnActerItemClickListener,
    VideoAdapter.OnVideoItemClickListener,
    MovieAdapter.OnItemClickListener{

    private lateinit var binding : ActivityDetailMovieInfoBinding
    private val viewModel: DetailMovieViewmodel by viewModels()

    private lateinit var countryAdapter: CountryAdapter
    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var castAdapter: CastAdapter
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie_info)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        setMovie()
        setObserve()
    }

    override fun onActerClick(id: Int) {

    }

    override fun onVideoItemClick(key: String) {
        startVideoActivity(key)
    }

    override fun onItemClick(id: Int) {

    }

    private fun setMovie(){
        val id = intent.getIntExtra("id",-1)
        if(id != -1){
            viewModel.getMovieData(id)
        }
    }
    private fun setObserve(){
        viewModel.backImg.observe(this){
            binding.imgBack
            val imageUrl ="https://image.tmdb.org/t/p/w500" + it
            Glide.with(binding.root).load(imageUrl).into(binding.imgBack)
        }
        viewModel.posterImg.observe(this){
            val imageUrl ="https://image.tmdb.org/t/p/w500" + it
            Glide.with(binding.root).load(imageUrl).into(binding.imgPoster)
        }
        viewModel.ratingPercentInt.observe(this){
            binding.circularProgressBar.progress = it.toFloat()
        }

        viewModel.countryList.observe(this){
            binding.countryRecycler.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,false)
            countryAdapter = CountryAdapter(it)
            binding.countryRecycler.adapter = countryAdapter
        }

        viewModel.genresList.observe(this){
            binding.recyclerGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
            genreAdapter = GenreAdapter(it)
            binding.recyclerGenre.adapter = genreAdapter
        }

        viewModel.companyList.observe(this){
            binding.recyclerCompany.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            companyAdapter = CompanyAdapter(it)
            binding.recyclerCompany.adapter = companyAdapter
        }

        viewModel.creditList.observe(this){
            binding.moviesActorsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            castAdapter = CastAdapter(it, this)
            binding.moviesActorsRecycler.adapter = castAdapter
        }

        viewModel.videoList.observe(this){
            binding.moviesVideoRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            videoAdapter = VideoAdapter(it, this)
            binding.moviesVideoRecycler.adapter = videoAdapter
        }

        viewModel.similarList.observe(this){
            binding.moviesSimilarMovieRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            movieAdapter = MovieAdapter(it,1,this)
            binding.moviesSimilarMovieRecycler.adapter = movieAdapter

        }
    }

    private fun startVideoActivity(key: String){
        val intent = Intent(this,VideoPlayActivity::class.java)
        intent.putExtra("videoKey",key)
        startActivity(intent)
    }

}