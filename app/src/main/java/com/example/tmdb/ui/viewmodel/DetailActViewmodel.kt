package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailActViewmodel @Inject constructor(application: Application): AndroidViewModel(application) {
    val actName : MutableLiveData<String> = MutableLiveData("")

    fun getActData(id: Int){

    }

}