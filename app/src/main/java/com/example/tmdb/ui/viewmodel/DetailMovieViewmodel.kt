package com.example.tmdb.ui.viewmodel

import android.app.Application
import android.os.Build.VERSION_CODES.M
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.credit.Cast
import com.example.tmdb.data.model.detailmovie.DetailsMovieResponse
import com.example.tmdb.data.model.detailmovie.Genre
import com.example.tmdb.data.model.detailmovie.ProductionCompany
import com.example.tmdb.data.model.video.VideoResult
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class DetailMovieViewmodel @Inject constructor(application: Application): AndroidViewModel(application) {
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
    val genresList : MutableLiveData<List<com.example.tmdb.data.model.detailmovie.Genre>> = MutableLiveData()
    val companyList : MutableLiveData<List<com.example.tmdb.data.model.detailmovie.ProductionCompany>> = MutableLiveData()

    val creditList : MutableLiveData<List<com.example.tmdb.data.model.credit.Cast>> = MutableLiveData()
    val videoList : MutableLiveData<List<com.example.tmdb.data.model.video.VideoResult>> = MutableLiveData()
    val similarList : MutableLiveData<List<com.example.tmdb.data.model.Result>> = MutableLiveData()

    val acterId : MutableLiveData<Int> = MutableLiveData()

    fun getMovieData(id : Int){
        viewModelScope.launch {
            val value = TMDBRetrofit.fetchDetailMovies(id)
            value?.let {
                setMovieData(it)
            }

            val credit = TMDBRetrofit.fetchCreditMovies(id)
            credit?.let {
                creditList.value = it.cast
            }

            val video = TMDBRetrofit.fetchVideoMovies(id)
            video?.let {
                videoList.value = it.results
            }

            val similar = TMDBRetrofit.fetchSimilarMovies(id)
            similar?.let {
                similarList.value = it.results
            }

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

    private fun setGenres(){

    }
    fun startActerActivity(id: Int){
        acterId.value = id
    }

    fun startMovieActivity(id: Int){

    }


    fun formatNumber(number: Int): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        return numberFormat.format(number)
    }

    fun onclickedbackImage(){

    }

    fun onclickedPoster(){

    }

    fun onclickedAllActors(){

    }

    fun onclickedAllSimilarMovies(){

    }

}