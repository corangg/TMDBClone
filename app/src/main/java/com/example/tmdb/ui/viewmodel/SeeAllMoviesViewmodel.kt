package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.R
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.detailactor.ActorCast
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SeeAllMoviesViewmodel @Inject constructor(application: Application): AndroidViewModel(application)  {

    val title : MutableLiveData<String> = MutableLiveData()
    val movieList : MutableLiveData<List<Result>> = MutableLiveData()
    val creditMovieList : MutableLiveData<List<ActorCast>> = MutableLiveData()
    val movieId : MutableLiveData<Int> = MutableLiveData()

    private var page : Int = 0

    fun getData(type: String, id: Int, actorid : Int){
        title.value = type
        viewModelScope.launch {
            page += 1
            when(type){
                getApplication<Application>().getString(R.string.nowplaying)->{
                    TMDBRetrofit.fetchNowPlayingMovies(page)?.let {
                        movieList.value = it
                    }
                }
                getApplication<Application>().getString(R.string.popular)->{
                    TMDBRetrofit.fetchPopularMovies(page)?.let {
                        movieList.value = it
                    }
                }
                getApplication<Application>().getString(R.string.topRated)->{
                    TMDBRetrofit.fetchTopRatedMovies(page)?.let {
                        movieList.value = it
                    }
                }
                getApplication<Application>().getString(R.string.upcoming)->{
                    TMDBRetrofit.fetchUpcomingMovies(page)?.let {
                        movieList.value = it
                    }
                }
                getApplication<Application>().getString(R.string.similar)->{
                    if(id != -1){
                        TMDBRetrofit.fetchSimilarMovies(id = id, page = page)?.let {
                            movieList.value = it
                        }
                    }
                }
                getApplication<Application>().getString(R.string.credit)->{
                    TMDBRetrofit.fetchActorCredit(id = actorid, page = page)?.let {
                        creditMovieList.value = it
                    }
                }
            }
        }
    }

    fun startMovieActivity(id : Int){
        movieId.value = id
    }
}