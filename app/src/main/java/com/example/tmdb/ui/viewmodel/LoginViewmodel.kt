package com.example.tmdb.ui.viewmodel

import android.app.Application
import android.os.Build.VERSION_CODES.M
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.repository.GetDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    application: Application,
    private val getDataRepository: GetDataRepository): AndroidViewModel(application) {

    val id : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()
    val startMainActivity : MutableLiveData<Unit> = MutableLiveData()
    init {
        getData()
    }

    fun getData() = viewModelScope.launch {
        getDataRepository.getData()
    }


    fun signIn(){

    }

    fun signUp(){

    }

    fun servieceContinue(){
        startMainActivity.value = Unit
    }
}