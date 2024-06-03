package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.R
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.data.model.credit.Cast
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
    val creditList : MutableLiveData<List<Cast>> = MutableLiveData()

    var page : Int = 0
    var accountID = -1

    fun getData(type: String, moiveID : Int) = viewModelScope.launch {
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
            getApplication<Application>().getString(R.string.credit)->{
                val getlist = TMDBRetrofit.fetchCreditMovies(moiveID, page)
                getlist?.let {
                    creditList.value = it.cast
                }
            }
        }
    }

    fun startMovieActivity(id : Int){
        actorId.value = id
    }
}