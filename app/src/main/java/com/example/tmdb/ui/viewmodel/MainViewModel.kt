package com.example.tmdb.ui.viewmodel

import android.app.Application
import android.view.MenuItem
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.tmdb.R
import com.example.tmdb.model.Result
import com.example.tmdb.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application): AndroidViewModel(application) {
    val selectNavigationItem : MutableLiveData<Int> = MutableLiveData(0)

    val moviesList : MutableLiveData<List<Result>> = MutableLiveData()
    val nowPlayingList : MutableLiveData<List<Result>> = MutableLiveData()
    val popularList : MutableLiveData<List<Result>> = MutableLiveData()
    val topRatedList : MutableLiveData<List<Result>> = MutableLiveData()
    val upComingList : MutableLiveData<List<Result>> = MutableLiveData()

    val startSeeAllActivity : MutableLiveData<String> = MutableLiveData()

    val movieId : MutableLiveData<Int> = MutableLiveData()
    init {
        getData()
    }


    fun bottomNavigationItemSelected(item : MenuItem):Boolean{

        when(item.itemId){
            R.id.navigation_movies->{
                selectNavigationItem.value = 0
                return true
            }
            R.id.navigation_celebrities->{
                selectNavigationItem.value = 1
                return true
            }
            R.id.navigation_search->{
                selectNavigationItem.value = 2
                return true
            }
            R.id.navigation_profile->{
                selectNavigationItem.value = 3
                return true
            }
        }
        return false
    }

    fun getData(){
        viewModelScope.launch {
            TMDBRetrofit.fetchMovies()?.let {
                moviesList.value = it
            }
            TMDBRetrofit.fetchNowPlayingMovies()?.let {
                nowPlayingList.value = it
            }
            TMDBRetrofit.fetchPopularMovies()?.let {
                popularList.value = it
            }
            TMDBRetrofit.fetchTopRatedMovies()?.let {
                topRatedList.value = it
            }

            TMDBRetrofit.fetchUpcomingMovies()?.let {
                upComingList.value = it
            }

        }
    }

    fun startActivity(id : Int){
        movieId.value = id
    }

    fun nowPlayingSeeAll(){
        startSeeAllActivity.value = "NowPlaying"

    }

}