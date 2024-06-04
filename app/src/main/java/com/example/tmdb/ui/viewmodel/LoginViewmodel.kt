package com.example.tmdb.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.repository.GetLoginDataRepository
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import com.example.tmdb.data.model.account.CreateSessionBody
import com.example.tmdb.data.model.account.ValidateTokenBody
import com.example.tmdb.data.repository.SetAccountDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    application: Application,
    private val setAccountDataRepository: SetAccountDataRepository,
    private val getLoginDataRepository: GetLoginDataRepository,
    private val getDataRepository: GetDataRepository): AndroidViewModel(application) {

    val id : MutableLiveData<String> = MutableLiveData()
    val password : MutableLiveData<String> = MutableLiveData()
    val sessionId : MutableLiveData<String> = MutableLiveData()

    val startGuest : MutableLiveData<Unit> = MutableLiveData()
    val openSignUpPage : MutableLiveData<Unit> = MutableLiveData()


    init {
        loginCheck()
        getTMDBData()
    }

    fun signUp(){
        openSignUpPage.value = Unit
    }

    fun clickedSignIn() = viewModelScope.launch{
        val idValue = id.value
        val passwordValue = password.value

        if(idValue != null && passwordValue != null) {
            setAccountDataRepository.signIn(idValue,passwordValue)?.let {
                sessionId.value = it
                saveLoginData(idValue, passwordValue)
            }
        }
    }

    private fun loginCheck() = viewModelScope.launch{
        val id = getLoginDataRepository.getID()
        id?.let {IDData->
            setAccountDataRepository.signIn(IDData.ID, IDData.Password)?.let {
                sessionId.value = it
                saveLoginData(IDData.ID, IDData.Password)
            }
        }
    }

    private fun saveLoginData(id : String, password : String) = viewModelScope.launch {
        val idData = IDData(id, password)
        getLoginDataRepository.insertID(idData)
    }

    private fun getTMDBData() = viewModelScope.launch {
        getDataRepository.getData()
    }

    fun servieceContinue(){
        startGuest.value = Unit
    }
}