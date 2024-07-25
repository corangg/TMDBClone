package com.example.tmdb.presentation.ui.activity

import android.util.Log
import com.example.img_decorat.ui.base.BaseActivity
import com.example.tmdb.R
import com.example.tmdb.data.model.ID.IDSQL
import com.example.tmdb.data.model.ID.RoomTest
import com.example.tmdb.data.model.ID.User
import com.example.tmdb.data.source.remot.IDSQlDao
import com.example.tmdb.databinding.ActivityLoginBinding
import com.example.tmdb.presentation.viewmodel.LoginViewmodel
import com.example.tmdb.util.StartActivityUtil.startMainActivity
import com.example.tmdb.util.TMDBUrl
import com.example.tmdb.util.Util.openInternetPage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewmodel>() {
    override fun layoutResId() = R.layout.activity_login

    override fun getViewModelClass() = LoginViewmodel::class.java

    override fun initializeUI() {
        binding.viewmodel = viewModel
        testSQL()
        //realm()
        //room()

        /*viewModel.measureRoomInsertTime { roomTime ->
            Log.d("PerformanceTest", "Room insert time: $roomTime ms")
        }

        viewModel.measureRealmInsertTime { realmTime ->
            Log.d("PerformanceTest", "Realm insert time: $realmTime ms")
        }*/
        viewModel.measureRoomReadTime { roomTime, roomSize, fileSize ->
            Log.d("PerformanceTest", "Room read time: $roomTime ms, size: $roomSize, fileSize: $fileSize")
        }

        viewModel.measureRealmReadTime { realmTime, realmSize, fileSize ->
            Log.d("PerformanceTest", "Realm read time: $realmTime ms, size: $realmSize, fileSize: $fileSize")
        }

        viewModel.measureRoomUpdateTime { roomTime->
            Log.d("PerformanceTest", "Room update time: $roomTime ms")
        }

        viewModel.measureRealmUpdateTime { realmTime->
            Log.d("PerformanceTest", "Realm update time: $realmTime ms")
        }
    }

    override fun setObserve() {
        viewModel.startMainActivity.observe(this) {
            startMainActivity(this, viewModel.sessionId)
            finish()
        }
        viewModel.openSignUpPage.observe(this) {
            openInternetPage(this, TMDBUrl.signUpUrl)
        }
    }

    private lateinit var idDao: IDSQlDao
    private fun testSQL() {
        idDao = IDSQlDao(this)

        val newData = IDSQL(id = "user1", password = "password123")
        idDao.insertIDData(newData)

        val retrievedData = idDao.getIDData()
        if (retrievedData != null) {
            Log.d("MainActivity", "ID: ${retrievedData.id}, Password: ${retrievedData.password}")
        } else {
            Log.d("MainActivity", "No data found")
        }
    }
}



