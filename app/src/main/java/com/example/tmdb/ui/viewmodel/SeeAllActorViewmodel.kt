package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.data.model.detailactor.ActorCast
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllActorViewmodel @Inject constructor(application: Application): AndroidViewModel(application){
    val title : MutableLiveData<String> = MutableLiveData("")

    val actorId : MutableLiveData<Int> = MutableLiveData()
    val actorList : MutableLiveData<List<CelebritiesResult>> = MutableLiveData()

    var page : Int = 0

    fun getData(type: String) = viewModelScope.launch {
        title.value = type
        page += 1
        when(type){
            "Popular"->{
                val getlist = TMDBRetrofit.fetchPopularCelebrities(page)
                getlist?.let {
                    actorList.value = it
                }
            }
            "Trending"->{
                val getlist = TMDBRetrofit.fetchTrendingCelebrities(page)
                getlist?.let {
                    actorList.value = it
                }
            }
        }
    }

    fun startMovieActivity(id : Int){
        actorId.value = id
    }
}