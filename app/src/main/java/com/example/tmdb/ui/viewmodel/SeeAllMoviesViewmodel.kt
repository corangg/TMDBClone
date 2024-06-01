package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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

    fun getData(type: String, id: Int){
        title.value = type
        viewModelScope.launch {
            page += 1
            when(type){
                "NowPlaying"->{
                    val getlist = TMDBRetrofit.fetchNowPlayingMovies(page)
                    getlist?.let {
                        movieList.value = it
                    }
                }
                "Popular"->{
                    val getlist = TMDBRetrofit.fetchPopularMovies(page)
                    getlist?.let {
                        movieList.value = it
                    }
                }
                "Top Rated"->{
                    val getlist = TMDBRetrofit.fetchTopRatedMovies(page)
                    getlist?.let {
                        movieList.value = it
                    }
                }
                "Upcoming"->{
                    val getlist = TMDBRetrofit.fetchUpcomingMovies(page)
                    getlist?.let {
                        movieList.value = it
                    }
                }
                "Similar"->{
                    if(id != -1){
                        val getlist = TMDBRetrofit.fetchSimilarMovies(id = id, page = page)
                        getlist?.let {
                            movieList.value = it
                        }
                    }
                }
                "credits"->{
                    val getlist = TMDBRetrofit.fetchActorCredit(page)
                    getlist?.let {
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