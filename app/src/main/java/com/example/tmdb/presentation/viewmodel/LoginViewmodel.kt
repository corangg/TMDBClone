package com.example.tmdb.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.model.ID.RoomTest
import com.example.tmdb.data.model.ID.User
import com.example.tmdb.data.repository.GetDataRepository
import com.example.tmdb.data.repository.GetLoginDataRepository
import com.example.tmdb.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
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

    //Room
    fun measureRoomInsertTime(onResult: (Long) -> Unit) = viewModelScope.launch {
        val startTime = System.currentTimeMillis()
        withContext(Dispatchers.IO) {
            for (i in 0..10000) {
                val id = RoomTest(i, i.toString(), i + 1, (i + 1).toString())
                getLoginDataRepository.testInsert(id)
            }
        }
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        onResult(elapsedTime)
    }

    fun measureRoomReadTime(onResult: (Long, Int, Long) -> Unit) = viewModelScope.launch {
        val startTime = System.currentTimeMillis()
        val userList: List<RoomTest>
        withContext(Dispatchers.IO) {
            userList = getLoginDataRepository.testGet()
        }
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        val dbFile = File(getApplication<Application>().getDatabasePath("id_dat").path)
        val dbSize = dbFile.length()
        onResult(elapsedTime, userList.size, dbSize)
    }

    fun measureRoomUpdateTime(onResult: (Long) -> Unit) = viewModelScope.launch {

        val startTime = System.currentTimeMillis()
        withContext(Dispatchers.IO) {
            for (i in 0..10000) {
                val id = RoomTest(i, i.toString(), i + 2, (i + 1).toString())
                getLoginDataRepository.testUpdate(id)
            }
        }
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        onResult(elapsedTime)
    }

    //Realm
    fun measureRealmInsertTime(onResult: (Long) -> Unit) = viewModelScope.launch {
        val startTime = System.currentTimeMillis()
        withContext(Dispatchers.IO) {
            val realm = Realm.getDefaultInstance()
            try {
                for (i in 0..10000) {
                    val id = User(i, i.toString(), i + 1, (i + 1).toString())
                    realm.executeTransaction { transactionRealm ->
                        transactionRealm.insert(id)
                    }
                }
            } finally {
                if (!realm.isClosed) {
                    realm.close()
                }
            }
        }
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        onResult(elapsedTime)
    }


    fun measureRealmReadTime(onResult: (Long, Int, Long) -> Unit) = viewModelScope.launch {
        val startTime = System.currentTimeMillis()
        val userList: List<User>
        val realmFile: File
        withContext(Dispatchers.IO) {
            val realm = Realm.getDefaultInstance()
            try {
                userList = realm.where(User::class.java).findAll()
                realmFile = File(realm.configuration.path)
                val size = userList.size
                val endTime = System.currentTimeMillis()
                val elapsedTime = endTime - startTime
                val dbSize = realmFile.length()
                onResult(elapsedTime, size, dbSize)
            } finally {
                realm.close()
            }
        }
    }


    fun measureRealmUpdateTime(onResult: (Long) -> Unit) = viewModelScope.launch {
        val startTime = System.currentTimeMillis()
        withContext(Dispatchers.IO) {
            val realm = Realm.getDefaultInstance()
            try {
                realm.executeTransaction { transactionRealm ->
                    for (i in 0..10000) {
                        val user = transactionRealm.where(User::class.java).equalTo("id", i).findFirst()
                        user?.let {
                            it.name = "Updated Name $i"
                            it.age = (i + 10)
                        }
                    }
                }

            } finally {
                if (!realm.isClosed) {
                    realm.close()
                }
            }
        }
        val endTime = System.currentTimeMillis()
        val elapsedTime = endTime - startTime
        onResult(elapsedTime)
    }


}