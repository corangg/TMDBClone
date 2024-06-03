package com.example.tmdb.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.credit.Cast
import com.example.tmdb.data.model.detailmovie.Genre
import com.example.tmdb.data.model.detailmovie.ProductionCompany
import com.example.tmdb.data.model.video.VideoResult
import com.example.tmdb.databinding.ActivityDetailMovieInfoBinding
import com.example.tmdb.ui.adapter.CastAdapter
import com.example.tmdb.ui.adapter.CompanyAdapter
import com.example.tmdb.ui.adapter.CountryAdapter
import com.example.tmdb.ui.adapter.GenreAdapter
import com.example.tmdb.ui.adapter.MovieAdapter
import com.example.tmdb.ui.adapter.VideoAdapter
import com.example.tmdb.ui.viewmodel.DetailMovieViewmodel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieInfoActivity : AppCompatActivity(),
    ItemClickInterface{
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

    override fun onVideoItemClick(key: String) {
        startVideoActivity(key)
    }

    override fun onMovieItemClick(id: Int) {
        viewModel.startMovieActivity(id)
    }

    override fun onActorItemClick(id: Int) {
        viewModel.startActerActivity(id)
    }

    private fun setMovie(){
        val id = intent.getIntExtra(getString(R.string.movieID),-1)
        if(id != -1){
            viewModel.getMovieData(id)
        }
    }

    private fun setObserve(){
        viewModel.backImg.observe(this){
            Util.setImage(it, binding.root, binding.imgBack)
        }
        viewModel.posterImg.observe(this){
            Util.setImage(it, binding.root, binding.imgPoster)
        }
        viewModel.ratingPercentInt.observe(this){
            binding.circularProgressBar.progress = it.toFloat()
        }
        viewModel.countryList.observe(this){
            setContryAdapter(it)
        }
        viewModel.genresList.observe(this){
            setGenresAdapter(it)
        }
        viewModel.companyList.observe(this){
            setCompanyAdapter(it)
        }
        viewModel.creditList.observe(this){
            setCastAdapter(it)
        }
        viewModel.videoList.observe(this){
            setVideoAdapter(it)
        }
        viewModel.similarList.observe(this){
            setMovieAdapter(it)
        }
        viewModel.movieId.observe(this){
            startMovieActivity(it)
        }

        viewModel.acterId.observe(this){
            startDetailActorActivity(it)
        }

        viewModel.fullImage.observe(this){
            startFullImageActivity(it)
        }

        viewModel.startSeeAllMovieActivity.observe(this){
            startSeeALlMovieActivity(it)
        }
    }

    private fun setContryAdapter(list: List<String>){
        binding.countryRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        countryAdapter = CountryAdapter(list)
        binding.countryRecycler.adapter = countryAdapter
    }

    private fun setGenresAdapter(list: List<Genre>){
        binding.recyclerGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        genreAdapter = GenreAdapter(list)
        binding.recyclerGenre.adapter = genreAdapter
    }

    private fun setCompanyAdapter(list: List<ProductionCompany>){
        binding.recyclerCompany.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        companyAdapter = CompanyAdapter(list)
        binding.recyclerCompany.adapter = companyAdapter
    }

    private fun setCastAdapter(list: List<Cast>){
        binding.moviesActorsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        castAdapter = CastAdapter(list, this)
        binding.moviesActorsRecycler.adapter = castAdapter
    }

    private fun setVideoAdapter(list: List<VideoResult>){
        binding.moviesVideoRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        videoAdapter = VideoAdapter(list, this)
        binding.moviesVideoRecycler.adapter = videoAdapter
    }

    private fun setMovieAdapter(list: List<Result>){
        binding.moviesSimilarMovieRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        movieAdapter = MovieAdapter(list,1,this)
        binding.moviesSimilarMovieRecycler.adapter = movieAdapter
    }

    private fun startMovieActivity(id: Int){
        val intent = Intent(this,DetailMovieInfoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun startDetailActorActivity(id: Int){
        val intent = Intent(this,DetailActorInfoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun startVideoActivity(key: String){
        val intent = Intent(this,VideoPlayActivity::class.java)
        intent.putExtra("videoKey",key)
        startActivity(intent)
    }

    private fun startFullImageActivity(img : String){
        val intent = Intent(this, FullImageActivity::class.java)
        intent.putExtra("img",img)
        startActivity(intent)
    }

    private fun startSeeALlMovieActivity(type : String){
        val id = intent.getIntExtra("id",-1)
        if(id != -1){
            val intent = Intent(this, SeeAllMoviesActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("type",type)
            startActivity(intent)
        }
    }
}