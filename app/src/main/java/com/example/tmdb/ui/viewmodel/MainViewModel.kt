package com.example.tmdb.ui.viewmodel

import android.app.Application
import android.view.MenuItem
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.R
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val getDataRepository: GetDataRepository): AndroidViewModel(application) {
    val selectNavigationItem : MutableLiveData<Int> = MutableLiveData(0)

    val liveMoviesList : MutableLiveData<List<Result>> = MutableLiveData()
    val liveMoviesNowPlayingList : MutableLiveData<List<Result>> = MutableLiveData()
    val liveMoviesPopularList : MutableLiveData<List<Result>> = MutableLiveData()
    val liveMoviesTopRatedList : MutableLiveData<List<Result>> = MutableLiveData()
    val liveMoviesUpComingList : MutableLiveData<List<Result>> = MutableLiveData()
    val liveCelebritiesPopularList : MutableLiveData<List<CelebritiesResult>> = MutableLiveData()
    val liveCelebritiesTrendingList : MutableLiveData<List<CelebritiesResult>> = MutableLiveData()

    val startSeeAllMovieActivity : MutableLiveData<String> = MutableLiveData()
    val startSeeAllActorActivity : MutableLiveData<String> = MutableLiveData()

    val movieId : MutableLiveData<Int> = MutableLiveData()
    val actorId : MutableLiveData<Int> = MutableLiveData()

    var page : Int = 0


    fun bottomNavigationItemSelected(item : MenuItem):Boolean{

        when(item.itemId){
            R.id.navigation_movies->{
                selectNavigationItem.value = 0
                return true
            }
            R.id.navigation_celebrities->{
                selectNavigationItem.value = 1
                setCelebritiesList()
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

    fun setMoviesList(){
        liveMoviesList.value = getDataRepository.moviesList
        liveMoviesNowPlayingList.value = getDataRepository.moviesNowPlayingList
        liveMoviesPopularList.value = getDataRepository.moviesPopularList
        liveMoviesTopRatedList.value = getDataRepository.moviesTopRatedList
        liveMoviesUpComingList.value = getDataRepository.moviesUpComingList
    }

    private fun setCelebritiesList(){
        liveCelebritiesPopularList.value = getDataRepository.celebritiesPopularList
        liveCelebritiesTrendingList.value = getDataRepository.celebritiesTrendingList
    }


    fun startMovieActivity(id : Int){
        movieId.value = id
    }

    fun startActorActivity(id : Int){
        actorId.value = id
    }

    fun nowPlayingSeeAll(){
        startSeeAllMovieActivity.value = "NowPlaying"
    }

    fun popularSeeAll(){
        startSeeAllMovieActivity.value = "Popular"
    }

    fun topRatedSeeAll(){
        startSeeAllMovieActivity.value = "Top Rated"
    }

    fun upcomingSeeAll(){
        startSeeAllMovieActivity.value = "Upcoming"
    }

    fun celebritiesPopularSeeAll(){
        startSeeAllActorActivity.value = "Popular"
    }

    fun celebritiesTrendingSeeAll(){
        startSeeAllActorActivity.value = "Trending"
    }


    val searchKeyword : MutableLiveData<String> = MutableLiveData()
    val searchAny : MutableLiveData<Int> = MutableLiveData(0)
    val textSearchAny : MutableLiveData<String> = MutableLiveData("Search Any Movie")
    val searchMovieList : MutableLiveData<List<Result>> = MutableLiveData()
    val searchActorList : MutableLiveData<List<CelebritiesResult>> = MutableLiveData()

    fun selectSearchAny(type: Int){
        when(type){
            0->{
                searchAny.value = type
                textSearchAny.value = "Search Any Movie"
            }
            1->{
                searchAny.value = type
                textSearchAny.value = "Search Any Actor"
            }
        }
    }

    fun searchKeyword() = viewModelScope.launch{
        val keyword = searchKeyword.value
        page = 1
        keyword?.let {
           getList(it)
        }
    }

    fun getMorePage(){
        val keyword = searchKeyword.value
        page += 1
        keyword?.let {
            getList(it)
        }
    }

    private fun getList(keyWord : String) = viewModelScope.launch{
        when(searchAny.value){
            0->{
                val getList  = TMDBRetrofit.fetchSearchMovie(keyWord,page)
                getList?.let {
                    searchMovieList.value = it
                }
            }
            1->{
                val getList  = TMDBRetrofit.fetchSearchActor(keyWord,page)
                getList?.let {
                    searchActorList.value = it
                }
            }
        }
    }

    val log : MutableLiveData<String> = MutableLiveData("Log in")
    val name : MutableLiveData<String> = MutableLiveData("Name")
    val id : MutableLiveData<String> = MutableLiveData("dlwprkdlq")

    fun checkLogin(check : Boolean){
        if(check){
            log.value = "Log out"
        }else{
            log.value = "Log in"
        }
    }



}