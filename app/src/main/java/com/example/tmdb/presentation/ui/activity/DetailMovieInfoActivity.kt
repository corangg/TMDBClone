package com.example.tmdb.presentation.ui.activity

import android.content.res.ColorStateList
import com.example.img_decorat.ui.base.BaseActivity
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityDetailMovieInfoBinding
import com.example.tmdb.presentation.ui.adapter.CastAdapter
import com.example.tmdb.presentation.ui.adapter.CompanyAdapter
import com.example.tmdb.presentation.ui.adapter.CountryAdapter
import com.example.tmdb.presentation.ui.adapter.GenreAdapter
import com.example.tmdb.presentation.ui.adapter.MovieAdapter
import com.example.tmdb.presentation.ui.adapter.VideoAdapter
import com.example.tmdb.presentation.ui.fragment.GiveRatingFragment
import com.example.tmdb.presentation.viewmodel.DetailMovieViewmodel
import com.example.tmdb.util.ItemClickInterface
import com.example.tmdb.util.StartActivityUtil.startDetailActorInfoActivity
import com.example.tmdb.util.StartActivityUtil.startDetailMovieInfoActivity
import com.example.tmdb.util.StartActivityUtil.startFullImageActivity
import com.example.tmdb.util.StartActivityUtil.startLoginActivity
import com.example.tmdb.util.StartActivityUtil.startSeeAllActorActivity
import com.example.tmdb.util.StartActivityUtil.startSeeAllMovieActivity
import com.example.tmdb.util.StartActivityUtil.startVideoActivity
import com.example.tmdb.util.Util.getMovieID
import com.example.tmdb.util.Util.setImage
import com.example.tmdb.util.Util.setLinearAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieInfoActivity :
    BaseActivity<ActivityDetailMovieInfoBinding, DetailMovieViewmodel>(),
    ItemClickInterface {

    private lateinit var countryAdapter: CountryAdapter
    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var castAdapter: CastAdapter
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var movieAdapter: MovieAdapter

    private var movieId = -1

    override fun layoutResId() = R.layout.activity_detail_movie_info

    override fun getViewModelClass() = DetailMovieViewmodel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        setMovie()
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

    override fun setObserve() {
        viewModel.backImg.observe(this) {
            setImage(it, binding.root, binding.imgBack)
        }
        viewModel.posterImg.observe(this) {
            setImage(it, binding.root, binding.imgPoster)
        }
        viewModel.ratingPercentInt.observe(this) {
            binding.circularProgressBar.progress = it.toFloat()
        }
        viewModel.countryList.observe(this) {
            countryAdapter = CountryAdapter(it)
            setLinearAdapter(binding.countryRecycler, this, 1, countryAdapter)
        }
        viewModel.genresList.observe(this) {
            genreAdapter = GenreAdapter(it)
            setLinearAdapter(binding.recyclerGenre, this, 1, genreAdapter)
        }
        viewModel.companyList.observe(this) {
            companyAdapter = CompanyAdapter(it)
            setLinearAdapter(binding.recyclerCompany, this, 1, companyAdapter)
        }
        viewModel.creditList.observe(this) {
            castAdapter = CastAdapter(it, this)
            setLinearAdapter(binding.moviesActorsRecycler, this, 1, castAdapter)
        }
        viewModel.videoList.observe(this) {
            videoAdapter = VideoAdapter(it, this)
            setLinearAdapter(binding.moviesVideoRecycler, this, 1, videoAdapter)
        }
        viewModel.similarList.observe(this) {
            movieAdapter = MovieAdapter(it, 1, this)
            setLinearAdapter(binding.moviesSimilarMovieRecycler, this, 1, movieAdapter)
        }
        viewModel.selectMovieId.observe(this) {
            startDetailMovieInfoActivity(this, it)
        }

        viewModel.acterId.observe(this) {
            startDetailActorInfoActivity(this, it)
        }

        viewModel.fullImage.observe(this) {
            startFullImageActivity(this, it)
        }

        viewModel.startSeeAllActorActivity.observe(this) {
            startSeeAllActorActivity(this, it, movieId)
        }

        viewModel.startSeeAllMovieActivity.observe(this) {
            startSeeAllMovieActivity(this, it, movieId)
        }

        viewModel.startLoginActivity.observe(this) {
            startLoginActivity(this)
            finish()
        }

        viewModel.addWatchListCheck.observe(this) {
            if (it) {
                binding.btnBookmark.setBackgroundResource(R.drawable.ic_fill_bookmarker)
                binding.btnBookmark.backgroundTintList =
                    ColorStateList.valueOf(getColor(R.color.logincolor))

            } else {
                binding.btnBookmark.setBackgroundResource(R.drawable.ic_bookmark)
                binding.btnBookmark.backgroundTintList =
                    ColorStateList.valueOf(getColor(R.color.white))

            }
        }

        viewModel.startGiveRatingFragment.observe(this) {
            replaceFragment(binding.view.id, GiveRatingFragment(), true)
        }
    }

    private fun setMovie() {
        movieId = getMovieID(intent, this)
        if (movieId != -1) {
            viewModel.getMovieData(movieId)
            viewModel.checkWatchList(movieId)
        }
    }
}