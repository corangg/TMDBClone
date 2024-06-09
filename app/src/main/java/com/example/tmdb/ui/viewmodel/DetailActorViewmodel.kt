package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.R
import com.example.tmdb.data.model.detailactor.ActorCast
import com.example.tmdb.data.model.detailactor.DetailActorResponse
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailActorViewmodel @Inject constructor(application: Application) :
    AndroidViewModel(application) {
    val actName: MutableLiveData<String> = MutableLiveData("")
    val known: MutableLiveData<String> = MutableLiveData("")
    val place: MutableLiveData<String> = MutableLiveData("")
    val companies: MutableLiveData<String> = MutableLiveData("")
    val biography: MutableLiveData<String> = MutableLiveData("")
    val profile: MutableLiveData<String> = MutableLiveData("")

    val fullImage: MutableLiveData<String> = MutableLiveData()
    val startSeeAllMovieActivity: MutableLiveData<String> = MutableLiveData()

    val movieId: MutableLiveData<Int> = MutableLiveData()

    val creditList: MutableLiveData<List<ActorCast>> = MutableLiveData()

    fun getActData(id: Int) = viewModelScope.launch {
        TMDBRetrofit.fetchDetailActor(id)?.let {
            setActorData(it)
        }

        TMDBRetrofit.fetchActorCredit(id)?.let {
            creditList.value = it
        }
    }

    private fun setActorData(it: DetailActorResponse) {
        actName.value = it.name
        known.value = it.known_for_department
        place.value = it.place_of_birth
        companies.value = it.birthday
        biography.value = it.biography
        profile.value = it.profile_path
    }

    fun onclickedProfile() {
        fullImage.value = profile.value
    }

    fun startMovieActivity(id: Int) {
        movieId.value = id
    }

    fun creditSeeAll() {
        startSeeAllMovieActivity.value = getApplication<Application>().getString(R.string.credit)
    }
}