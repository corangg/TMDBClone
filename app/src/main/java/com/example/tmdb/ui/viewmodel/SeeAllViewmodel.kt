package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.model.Result
import com.example.tmdb.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SeeAllViewmodel @Inject constructor(application: Application): AndroidViewModel(application)  {

    val title : MutableLiveData<String> = MutableLiveData()
    val movieList : MutableLiveData<List<Result>> = MutableLiveData()

    var page : Int = 0
    val list = mutableListOf<Result>()

    fun getData(type: String){
        title.value = type
        viewModelScope.launch {
            page += 1
            when(type){
                "NowPlaying"->{
                    val getlist = TMDBRetrofit.fetchNowPlayingMovies(page)
                    getlist?.let {
                        list.addAll(it)
                        movieList.value = list
                    }
                }
            }
        }
    }
}