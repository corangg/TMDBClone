package com.example.tmdb.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.repository.GetLoginDataRepository
import com.example.tmdb.data.repository.SetAccountDataRepository
import com.example.tmdb.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    application: Application,
    private val getLoginDataRepository: GetLoginDataRepository,
    private val getDataRepository: GetDataRepository,
    private val signInUseCase: SignInUseCase
) : AndroidViewModel(application) {

    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    val startMainActivity: MutableLiveData<Unit> = MutableLiveData()
    val openSignUpPage: MutableLiveData<Unit> = MutableLiveData()


    init {
        loginCheck()
        getTMDBData()
    }

    private fun loginCheck() = viewModelScope.launch {
        val id = getLoginDataRepository.getID()
        id?.let { IDData ->
            signInUseCase.execute(IDData.id, IDData.password)?.let {
                saveLoginData(IDData.id, IDData.password)
                startMainActivity.value = Unit
            }
        }
    }

    private fun getTMDBData() = viewModelScope.launch {
        getDataRepository.getData()
    }

    private fun saveLoginData(id: String, password: String) = viewModelScope.launch {
        val idData = IDData(id, password)
        getLoginDataRepository.insertID(idData)
    }

    fun signUp() {
        openSignUpPage.value = Unit
    }

    fun clickedSignIn() = viewModelScope.launch {
        val idValue = id.value
        val passwordValue = password.value

        if (idValue != null && passwordValue != null) {
            signInUseCase.execute(idValue, passwordValue)?.let {
                saveLoginData(idValue, passwordValue)
                startMainActivity.value = Unit
            }
        }
    }

    fun servieceContinue() {
        startMainActivity.value = Unit
    }
}