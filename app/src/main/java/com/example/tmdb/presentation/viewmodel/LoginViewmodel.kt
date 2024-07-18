package com.example.tmdb.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.model.ID.User
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.repository.GetLoginDataRepository
import com.example.tmdb.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

    var sessionId = ""

    init {
        //loginCheck()
        getTMDBData()
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
                sessionId = it
                startMainActivity.value = Unit
            }
        }
    }

    fun servieceContinue() {
        startMainActivity.value = Unit
    }

    private fun loginCheck() = viewModelScope.launch {
        val id = getLoginDataRepository.getID()
        id?.let { IDData ->
            signInUseCase.execute(IDData.id, IDData.password)?.let {
                saveLoginData(IDData.id, IDData.password)
                sessionId = it
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

    private lateinit var realm: Realm

    fun realm() = viewModelScope.launch{
        realm = Realm.getDefaultInstance()
        GlobalScope.launch(Dispatchers.IO) {
            val startTime = System.currentTimeMillis()
            for(i in 0..1000){
                insertUserData(i)
            }
            val endTime = System.currentTimeMillis()
            Log.d("RealmTime", "$startTime ... $endTime")
            realm.close()
        }
    }

    private suspend fun insertUserData(id: Int) {
        realm.executeTransactionAwait(Dispatchers.IO) { realm ->
            val user = User(id)
            realm.insertOrUpdate(user)
            Log.d("MainActivity", "User inserted: $user")
        }
    }

}