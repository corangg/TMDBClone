package com.example.tmdb.ui.viewmodel

import android.app.Application
import android.os.Build.VERSION_CODES.M
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(application: Application): AndroidViewModel(application) {

    val id : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()
    val startMainActivity : MutableLiveData<Unit> = MutableLiveData()


    fun signIn(){

    }

    fun signUp(){

    }

    fun servieceContinue(){
        startMainActivity.value = Unit
    }
}