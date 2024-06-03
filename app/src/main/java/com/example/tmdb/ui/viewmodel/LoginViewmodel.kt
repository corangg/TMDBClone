package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.repository.GetLoginDataRepository
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import com.example.tmdb.data.test.CreateSessionBody
import com.example.tmdb.data.test.ValidateTokenBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    application: Application,
    private val getLoginDataRepository: GetLoginDataRepository,
    private val getDataRepository: GetDataRepository): AndroidViewModel(application) {

    val id : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()
    val sessionId : MutableLiveData<String> = MutableLiveData()

    val startGuest : MutableLiveData<Unit> = MutableLiveData()
    val openSignUpPage : MutableLiveData<Unit> = MutableLiveData()

    val loginfail : MutableLiveData<Boolean> = MutableLiveData()

    init {
        loginCheck()
        getTMDBData()
    }

    fun signUp(){
        openSignUpPage.value = Unit
    }

    fun clickedSignIn(){
        if(id.value != null && password.value != null)
        {
            signIn(id = id.value!!, password = password.value!!)
        }
    }

    private fun loginCheck() = viewModelScope.launch{
        val id = getLoginDataRepository.getID()
        id?.let {
            signIn(it.ID, it.Password)
        }
    }

    private fun saveLoginData(id : String, password : String) = viewModelScope.launch {
        val idData = IDData(id, password)
        getLoginDataRepository.insertID(idData)
    }

    private fun getTMDBData() = viewModelScope.launch {
        getDataRepository.getData()
    }

    private fun signIn(id : String, password : String){
        viewModelScope.launch {
            val token = TMDBRetrofit.createToken()
            token?.let {
                val body = ValidateTokenBody(username = id, password = password, request_token = it.request_token)
                val response = TMDBRetrofit.getRequestToken(body)
                if(response !=null){
                    TMDBRetrofit.fetchSession(CreateSessionBody(it.request_token))?.let {
                        saveLoginData(id, password)
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