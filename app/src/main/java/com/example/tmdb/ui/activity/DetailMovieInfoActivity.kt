package com.example.tmdb.ui.activity

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.tmdb.R
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
import com.example.tmdb.util.Util.ImgButtonSet
import com.example.tmdb.util.Util.getMovieID
import com.example.tmdb.util.Util.setLinearAdapter
import com.example.tmdb.util.Util.startDetailActorInfoActivity
import com.example.tmdb.util.Util.startDetailMovieInfoActivity
import com.example.tmdb.util.Util.startFullImageActivity
import com.example.tmdb.util.Util.startLoginActivity
import com.example.tmdb.util.Util.startSeeAllActorActivity
import com.example.tmdb.util.Util.startSeeAllMovieActivity
import com.example.tmdb.util.Util.startVideoActivity
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

    var accountID = -1
    var id = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie_info)
        (binding as ViewDataBinding).lifecycleOwner = this
        binding.viewmodel = viewModel

        accountID = Util.getAccountID(intent, this)
        setMovie()
        setObserve()
    }

    override fun onVideoItemClick(key: String) {
        startVideoActivity(this, key)
    }

    override fun onMovieItemClick(id: Int) {
        viewModel.startMovieActivity(id)
    }

    override fun onActorItemClick(id: Int) {
        viewModel.startActerActivity(id)
    }

    private fun setMovie(){
        id = getMovieID(intent, this)
        if(id != -1){
            viewModel.getMovieData(id)
            viewModel.setAccountID(accountID)
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
            countryAdapter = CountryAdapter(it)
            setLinearAdapter(binding.countryRecycler, this, 1, countryAdapter)
        }
        viewModel.genresList.observe(this){
            genreAdapter = GenreAdapter(it)
            setLinearAdapter(binding.recyclerGenre, this, 1, genreAdapter)
        }
        viewModel.companyList.observe(this){
            companyAdapter = CompanyAdapter(it)
            setLinearAdapter(binding.recyclerCompany, this, 1, companyAdapter)
        }
        viewModel.creditList.observe(this){
            castAdapter = CastAdapter(it, this)
            setLinearAdapter(binding.moviesActorsRecycler, this, 1, castAdapter)
        }
        viewModel.videoList.observe(this){
            videoAdapter = VideoAdapter(it, this)
            setLinearAdapter(binding.moviesVideoRecycler, this, 1, videoAdapter)
        }
        viewModel.similarList.observe(this){
            movieAdapter = MovieAdapter(it,1,this)
            setLinearAdapter(binding.moviesSimilarMovieRecycler, this, 1, movieAdapter)
        }
        viewModel.selectMovieId.observe(this){
            startDetailMovieInfoActivity(this, it, accountID)
        }

        viewModel.acterId.observe(this){
            startDetailActorInfoActivity(this, it, accountID)
        }

        viewModel.fullImage.observe(this){
            startFullImageActivity(this, it)
        }

        viewModel.startSeeAllActorActivity.observe(this){
            startSeeAllActorActivity(this, it, accountID, id)
        }

        viewModel.startSeeAllMovieActivity.observe(this){
            startSeeAllMovieActivity(this, it, accountID, id)
        }

        viewModel.startLoginActivity.observe(this){
            startLoginActivity(this)
            finish()
        }

        viewModel.addWatchListCheck.observe(this){
            if(it){
                ImgButtonSet(R.drawable.ic_fill_bookmarker, binding.root, binding.btnBookmark, getColor(R.color.logincolor))
            }else{
                ImgButtonSet(R.drawable.ic_bookmark, binding.root, binding.btnBookmark, getColor(R.color.white))
            }
        }
    }
}