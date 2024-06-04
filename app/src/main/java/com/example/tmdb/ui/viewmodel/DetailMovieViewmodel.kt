package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.R
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.credit.Cast
import com.example.tmdb.data.model.detailmovie.DetailsMovieResponse
import com.example.tmdb.data.model.detailmovie.Genre
import com.example.tmdb.data.model.detailmovie.ProductionCompany
import com.example.tmdb.data.model.video.VideoResult
import com.example.tmdb.data.model.watchlist.WatchListBody
import com.example.tmdb.data.repository.SetAccountDataRepository
import com.example.tmdb.data.repository.WatchListRepository
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class DetailMovieViewmodel @Inject constructor(
    application: Application,
    private val watchListRepository: WatchListRepository,
    private val setAccountDataRepository: SetAccountDataRepository,): AndroidViewModel(application) {
    val movieTitle : MutableLiveData<String> = MutableLiveData("")
    val backImg : MutableLiveData<String> = MutableLiveData("")
    val posterImg : MutableLiveData<String> = MutableLiveData("")
    val ratingPercentInt : MutableLiveData<Int> = MutableLiveData(0)
    val ratingPercent : MutableLiveData<String> = MutableLiveData("0%")
    val ratingStar : MutableLiveData<Float> = MutableLiveData(0f)
    val ratingNum : MutableLiveData<String> = MutableLiveData("(0)")
    val releaseDate : MutableLiveData<String> = MutableLiveData("")
    val language : MutableLiveData<String> = MutableLiveData("")
    val revenueValue : MutableLiveData<String> = MutableLiveData("0$")
    val overview : MutableLiveData<String> = MutableLiveData("")
    val countryList : MutableLiveData<List<String>> = MutableLiveData()
    val fullImage : MutableLiveData<String> = MutableLiveData()
    val startSeeAllMovieActivity : MutableLiveData<String> = MutableLiveData()
    val startSeeAllActorActivity : MutableLiveData<String> = MutableLiveData()

    val addWatchListCheck : MutableLiveData<Boolean> = MutableLiveData(false)

    val startLoginActivity : MutableLiveData<Unit> = MutableLiveData()

    val acterId : MutableLiveData<Int> = MutableLiveData()
    val selectMovieId : MutableLiveData<Int> = MutableLiveData()

    val genresList : MutableLiveData<List<Genre>> = MutableLiveData()
    val companyList : MutableLiveData<List<ProductionCompany>> = MutableLiveData()
    val creditList : MutableLiveData<List<Cast>> = MutableLiveData()
    val videoList : MutableLiveData<List<VideoResult>> = MutableLiveData()
    val similarList : MutableLiveData<List<Result>> = MutableLiveData()

    var movieId : Int = -1

    fun getMovieData(id : Int){
        movieId = id
        viewModelScope.launch {
            TMDBRetrofit.fetchDetailMovies(id)?.let {
                setMovieData(it)
            }

            TMDBRetrofit.fetchCreditMovies(id)?.let {
                creditList.value = it.cast
            }

            TMDBRetrofit.fetchVideoMovies(id)?.let {
                videoList.value = it.results
            }

            TMDBRetrofit.fetchSimilarMovies(id)?.let {
                similarList.value = it
            }
        }
    }

    fun checkWatchList(id: Int){
        if(setAccountDataRepository.checkWatchList(id)){
            addWatchListCheck.value = true
        }else{
            addWatchListCheck.value = false
        }
    }

    private fun setMovieData(it : DetailsMovieResponse){
        val percent = (it.vote_average*10).toInt()
        val ratingStars = it.vote_average/2
        val voteCount = it.vote_count
        val revenue = formatNumber(it.revenue)
        val overviewValue = it.overview
        movieTitle.value = it.title
        backImg.value = it.backdrop_path
        posterImg.value = it.poster_path
        ratingPercent.value = "$percent%"
        ratingPercentInt.value = percent
        ratingStar.value = ratingStars.toFloat()
        ratingNum.value = "($voteCount)"
        releaseDate.value = it.release_date
        language.value = it.spoken_languages[0].english_name
        revenueValue.value = "$revenue$"
        overview.value = " $overviewValue"
        countryList.value = it.origin_country
        genresList.value = it.genres
        companyList.value = it.production_companies
    }

    fun startActerActivity(id: Int){
        acterId.value = id
    }

    fun startMovieActivity(id: Int){
        selectMovieId.value = id
    }

    fun formatNumber(number: Int): String {
        return NumberFormat.getNumberInstance(Locale.US).format(number)
    }

    fun onclickedbackImage(){
        fullImage.value = backImg.value
    }

    fun onclickedPoster(){
        fullImage.value = posterImg.value
    }

    fun onclickedAllActors(){
        startSeeAllActorActivity.value = getApplication<Application>().getString(R.string.credit)
    }

    fun onclickedAllSimilarMovies(){
        startSeeAllMovieActivity.value = getApplication<Application>().getString(R.string.similar)
    }

    fun addWatchList()= viewModelScope.launch{
        if(setAccountDataRepository.accountId != -1){
            addWatchListCheck.value?.let {
                watchListRepository.addWatchList(it, movieId)?.let {check->
                    addWatchListCheck.value = !check
                }
            }
        }else{
            startLoginActivity.value = Unit
        }
    }
}