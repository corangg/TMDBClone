package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SeeAllViewmodel @Inject constructor(application: Application): AndroidViewModel(application)  {

    val title : MutableLiveData<String> = MutableLiveData()
    val movieList : MutableLiveData<List<com.example.tmdb.data.model.Result>> = MutableLiveData()

    var page : Int = 0
    val list = mutableListOf<com.example.tmdb.data.model.Result>()

    fun getData(type: String){
        title.value = type
        viewModelScope.launch {
            page += 1
            when(type){
                "NowPlaying"->{
                    val getlist = TMDBRetrofit.fetchNowPlayingMovies(page)
                    getlist?.let {
                        list.addAll(it)
                        movieList.value = it
                    }
                }
                "Popular"->{
                    val getlist = TMDBRetrofit.fetchPopularMovies(page)
                    getlist?.let {
                        list.addAll(it)
                        movieList.value = it
                    }
                }
                "Top Rated"->{
                    val getlist = TMDBRetrofit.fetchTopRatedMovies(page)
                    getlist?.let {
                        list.addAll(it)
                        movieList.value = it
                    }
                }
                "Upcoming"->{
                    val getlist = TMDBRetrofit.fetchUpcomingMovies(page)
                    getlist?.let {
                        list.addAll(it)
                        movieList.value = it
                    }
                }
            }
        }
    }


}