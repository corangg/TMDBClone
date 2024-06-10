package com.example.tmdb.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullImageViewmodel @Inject constructor(application: Application) :
    AndroidViewModel(application) {
    val back: MutableLiveData<Unit> = MutableLiveData()
    fun clickedBack() {
        back.value = Unit
    }
}