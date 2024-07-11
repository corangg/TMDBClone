package com.example.tmdb.presentation.viewmodel

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.R
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.celebrities.CelebritiesResult
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.repository.GetLoginDataRepository
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import com.example.tmdb.domain.usecase.FlowBuilder
import com.example.tmdb.domain.usecase.GetAccountIdUseCase
import com.example.tmdb.domain.usecase.GetMyWatchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val getDataRepository: GetDataRepository,
    private val getLoginDataRepository: GetLoginDataRepository,
    private val getAccountIdUseCase: GetAccountIdUseCase,
    private val getMyWatchListUseCase: GetMyWatchListUseCase,
    private val sharedPreferences: SharedPreferences,
    private val flowBuilder: FlowBuilder
) : AndroidViewModel(application) {

    val selectNavigationItem: MutableLiveData<Int> = MutableLiveData(0)
    val searchAny: MutableLiveData<Boolean> = MutableLiveData(false)
    val movieId: MutableLiveData<Int> = MutableLiveData()
    val actorId: MutableLiveData<Int> = MutableLiveData()
    val connectionIC: MutableLiveData<Int> = MutableLiveData()

    val startSeeAllMovieActivity: MutableLiveData<String> = MutableLiveData()
    val startSeeAllActorActivity: MutableLiveData<String> = MutableLiveData()
    val searchKeyword: MutableLiveData<String> = MutableLiveData()
    val textSearchAny: MutableLiveData<String> =
        MutableLiveData(getApplication<Application>().getString(R.string.search_movie))
    val log: MutableLiveData<String> =
        MutableLiveData(getApplication<Application>().getString(R.string.login))
    val name: MutableLiveData<String> =
        MutableLiveData(getApplication<Application>().getString(R.string.name))
    val id: MutableLiveData<String> = MutableLiveData("")

    val finishedLogoutCheckFragment: MutableLiveData<Boolean> = MutableLiveData()

    val setProfile: MutableLiveData<Unit> = MutableLiveData()
    val startLoginActivity: MutableLiveData<Unit> = MutableLiveData()
    val startSavedFragment: MutableLiveData<Unit> = MutableLiveData()
    val startLanguageFragment: MutableLiveData<Unit> = MutableLiveData()
    val startContactFragment: MutableLiveData<Unit> = MutableLiveData()
    val startAboutFragment: MutableLiveData<Unit> = MutableLiveData()
    val startCheckLogOutFragment: MutableLiveData<Unit> = MutableLiveData()

    val liveMoviesList: MutableLiveData<List<Result>> = MutableLiveData()
    val liveMoviesNowPlayingList: MutableLiveData<List<Result>> = MutableLiveData()
    val liveMoviesPopularList: MutableLiveData<List<Result>> = MutableLiveData()
    val liveMoviesTopRatedList: MutableLiveData<List<Result>> = MutableLiveData()
    val liveMoviesUpComingList: MutableLiveData<List<Result>> = MutableLiveData()
    val searchMovieList: MutableLiveData<List<Result>> = MutableLiveData()
    val savedList: MutableLiveData<List<Result>> = MutableLiveData()

    val liveCelebritiesPopularList: MutableLiveData<List<CelebritiesResult>> = MutableLiveData()
    val liveCelebritiesTrendingList: MutableLiveData<List<CelebritiesResult>> = MutableLiveData()
    val searchActorList: MutableLiveData<List<CelebritiesResult>> = MutableLiveData()

    private var page = 0

    private var accountId = -1

    fun bottomNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_movies -> {
                selectNavigationItem.value = 0
                return true
            }

            R.id.navigation_celebrities -> {
                selectNavigationItem.value = 1
                setCelebritiesList()
                return true
            }

            R.id.navigation_search -> {
                selectNavigationItem.value = 2
                return true
            }

            R.id.navigation_profile -> {
                selectNavigationItem.value = 3
                return true
            }
        }
        return false
    }

    fun setPorfileData(sessionId: String) = viewModelScope.launch {
        getAccountIdUseCase.execute(sessionId)?.let {
            setProfile.value = Unit
            log.value = getApplication<Application>().getString(R.string.logout)
            if (it.name != "") name.value = it.name
            id.value = it.username
            accountId = it.id
            sharedPreferences.edit()
                .putInt(getApplication<Application>().getString(R.string.accountID), it.id).apply()
        }
    }

    fun setMoviesList() {
        liveMoviesList.value = getDataRepository.moviesList
        liveMoviesNowPlayingList.value = getDataRepository.moviesNowPlayingList
        liveMoviesPopularList.value = getDataRepository.moviesPopularList
        liveMoviesTopRatedList.value = getDataRepository.moviesTopRatedList
        liveMoviesUpComingList.value = getDataRepository.moviesUpComingList
    }

    fun startMovieActivity(id: Int) {
        movieId.value = id
    }

    fun startActorActivity(id: Int) {
        actorId.value = id
    }

    fun nowPlayingSeeAll() {
        startSeeAllMovieActivity.value =
            getApplication<Application>().getString(R.string.nowplaying)
    }

    fun popularSeeAll() {
        startSeeAllMovieActivity.value = getApplication<Application>().getString(R.string.popular)
    }

    fun topRatedSeeAll() {
        startSeeAllMovieActivity.value = getApplication<Application>().getString(R.string.topRated)
    }

    fun upcomingSeeAll() {
        startSeeAllMovieActivity.value = getApplication<Application>().getString(R.string.upcoming)
    }

    fun celebritiesPopularSeeAll() {
        startSeeAllActorActivity.value = getApplication<Application>().getString(R.string.popular)
    }

    fun celebritiesTrendingSeeAll() {
        startSeeAllActorActivity.value = getApplication<Application>().getString(R.string.trending)
    }

    fun selectSearchAny(type: Boolean) {
        if (type) {
            searchAny.value = type
            textSearchAny.value = getApplication<Application>().getString(R.string.search_movie)
        } else {
            searchAny.value = type
            textSearchAny.value = getApplication<Application>().getString(R.string.search_actor)
        }

    }

    fun searchKeyword() = viewModelScope.launch {
        val keyword = searchKeyword.value
        page = 1
        keyword?.let {
            getList(it)
        }
    }

    fun getMorePage() {
        val keyword = searchKeyword.value
        page += 1
        keyword?.let {
            getList(it)
        }
    }

    fun checkLogOut() {
        if (accountId != -1) {
            startCheckLogOutFragment.value = Unit
        } else {
            startLoginActivity.value = Unit
        }
    }

    fun startLoginActivity(click: Boolean) {
        if (click) {
            deleteIDData()
            startLoginActivity.value = Unit
        } else {
            finishedLogoutCheckFragment.value = true
            finishedLogoutCheckFragment.value = false
        }
    }

    fun clickedSaved() {
        if (accountId != -1) {
            startSavedFragment.value = Unit
        } else {
            startLoginActivity.value = Unit
        }
    }

    fun clickedLanguage() {
        startLanguageFragment.value = Unit
    }

    fun clickedContact() {
        startContactFragment.value = Unit
    }

    fun clickedConnection(ic: Int) {
        connectionIC.value = ic
    }

    fun clickedAbout() {
        startAboutFragment.value = Unit
    }

    fun getMySavedList() = viewModelScope.launch {
        getMyWatchListUseCase.execute(accountId)?.let {
            savedList.value = it
        }
    }

    private fun setCelebritiesList() {
        liveCelebritiesPopularList.value = getDataRepository.celebritiesPopularList
        liveCelebritiesTrendingList.value = getDataRepository.celebritiesTrendingList
    }

    private fun getList(keyWord: String) = viewModelScope.launch {
        searchAny.value?.let {
            if (it) {
                TMDBRetrofit.fetchSearchMovie(keyWord, page)?.let {
                    searchMovieList.value = it
                }
            } else {
                TMDBRetrofit.fetchSearchActor(keyWord, page)?.let {
                    searchActorList.value = it
                }
            }
        }
    }

    private fun deleteIDData() = viewModelScope.launch {
        getLoginDataRepository.deleteID()
    }

    val a = MutableLiveData<List<Result>>()

    fun collect1() = viewModelScope.launch {
        flowBuilder("collect 1").collect {
            Log.d("collect", "collect 1")
            a.value = it
            val list = it
        }
    }

    fun collect2() = viewModelScope.launch {
        delay(3000)
        flowBuilder("collect 2").collect {
            Log.d("collect", "collect 2")
            val list = it
        }
    }

    fun collect3() = viewModelScope.launch {
        delay(6000)
        flowBuilder("collect 3").collect {
            Log.d("collect", "collect 3")
            val list = it
        }
    }

    private val _stateFlow = MutableStateFlow<List<Result>>(emptyList())
    val stateFlow: StateFlow<List<Result>> = _stateFlow

    /* init {
         setStateFlow()
         sharedFlow()
     }*/

    private fun setStateFlow() = viewModelScope.launch {
        val list = TMDBRetrofit.fetchNowPlayingMovies() ?: return@launch
        delay(5000)
        Log.d("stateFlow", "StateFlow")
        _stateFlow.value = list
    }

    fun stateFlowCollect1() = viewModelScope.launch {
        stateFlow.collect {
            Log.d("stateFlowCollect1", "${it.size}")
        }
    }


    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow: SharedFlow<Int> = _sharedFlow

    init {
        sharedFlow()
    }

    private fun sharedFlow() = viewModelScope.launch {
        var num = 0
        Log.d("sharedFlow", "sharedFlow")
        while (true) {
            num += 1
            _sharedFlow.emit(num)
            delay(1000)
        }
    }

    fun sharedFlowCollect1() = viewModelScope.launch {
        sharedFlow.collect {
            Log.d("sharedFlowCollect1", "$it")
        }
    }

    fun sharedFlowCollect2() = viewModelScope.launch {
        delay(4000)
        sharedFlow.collect {
            Log.d("sharedFlowCollect2", "$it")
        }
    }

   /* fun sharedFlowCollect3() = viewModelScope.launch {
        sharedFlow.collect {
            Log.d("sharedFlowCollect", "3 : ${it.size}")
        }
    }*/


    fun getData() = viewModelScope.launch {
        val list = TMDBRetrofit.fetchNowPlayingMovies() ?: return@launch
    }

}