package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import com.example.tmdb.data.test.CreateSessionBody
import com.example.tmdb.data.test.ValidateTokenBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    application: Application,
    private val getDataRepository: GetDataRepository): AndroidViewModel(application) {

    val id : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()
    val startGuest : MutableLiveData<Unit> = MutableLiveData()

    val loginfail : MutableLiveData<Boolean> = MutableLiveData()
    val sessionId : MutableLiveData<String> = MutableLiveData()

    init {
        getData()
    }

    fun getData() = viewModelScope.launch {
        getDataRepository.getData()
    }

    fun signUp(){

    }

    fun signIn(){
        viewModelScope.launch {
            val token = TMDBRetrofit.createToken()
            token?.let {
                val body = ValidateTokenBody(username = id.value!!, password = password.value!!, request_token = token.request_token)
                val response = TMDBRetrofit.getRequestToken(body)
                if(response !=null){
                    TMDBRetrofit.fetchSession(CreateSessionBody(it.request_token))?.let {
                        sessionId.value = it.session_id
                    }
                }
                else{
                    loginfail.value = true
                }
            }
        }
    }

    fun servieceContinue(){
        startGuest.value = Unit
    }
}