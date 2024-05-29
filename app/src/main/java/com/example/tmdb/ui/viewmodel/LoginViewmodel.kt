package com.example.tmdb.ui.viewmodel

import android.app.Application
import android.os.Build.VERSION_CODES.M
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class LoginViewmodel(application: Application): AndroidViewModel(application) {

    val id : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()


    fun signIn(){

    }

    fun signUp(){

    }

    fun servieceContinue(){

    }

}