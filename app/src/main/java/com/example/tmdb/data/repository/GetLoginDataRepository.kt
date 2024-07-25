package com.example.tmdb.data.repository

import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.model.ID.RoomTest
import com.example.tmdb.data.source.local.IDDao
import com.example.tmdb.data.source.local.TestDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLoginDataRepository @Inject constructor(
    private val idDao: IDDao,
    private val testDao: TestDao
) {

    suspend fun insertID(id: IDData) {
        idDao.insertIDData(id)
    }

    suspend fun getID(): IDData? {
        return idDao.getIDData()
    }

    suspend fun deleteID() {
        idDao.deleteIDData()
    }

    suspend fun testInsert(data: RoomTest){
        testDao.insertTest(data)
    }

    suspend fun testUpdate(data: RoomTest){
        testDao.updateTest(data)
    }

    suspend fun testGet(): List<RoomTest>{
        return testDao.getAllUsers()
    }

    suspend fun measureRoomReadTime(): Long {
        val startTime = System.currentTimeMillis()
        withContext(Dispatchers.IO) {
            testDao.getAllUsers()
        }
        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }
}
