package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailMovieViewmodel @Inject constructor(application: Application): AndroidViewModel(application) {
    val movieTitle : MutableLiveData<String> = MutableLiveData("")
    val ratingPercent : MutableLiveData<String> = MutableLiveData("0%")
    val ratingStar : MutableLiveData<Float> = MutableLiveData(0f)
    val ratingNum : MutableLiveData<String> = MutableLiveData("(0)")
    val releaseDate : MutableLiveData<String> = MutableLiveData("")
    val language : MutableLiveData<String> = MutableLiveData("")
    val revenueValue : MutableLiveData<String> = MutableLiveData("0$")
    val overview : MutableLiveData<String> = MutableLiveData("")

    fun getMovieData(){

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