package com.example.tmdb.data.repository

import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.source.local.IDDao
import javax.inject.Inject

class GetLoginDataRepository @Inject constructor(
    private val idDao:IDDao) {

    suspend fun insertID(id : IDData){
        idDao.insertIDData(id)
    }

    suspend fun getID(): IDData?{
        return idDao.getIDData()
    }

    suspend fun deleteID(){
        idDao.deleteIDData()
    }
}