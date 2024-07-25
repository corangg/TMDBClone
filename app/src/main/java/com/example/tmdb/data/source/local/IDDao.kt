package com.example.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.model.ID.RoomTest

@Dao
interface IDDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIDData(idDta: IDData)

    @Query("SELECT * FROM id")
    suspend fun getIDData(): IDData?

    @Query("DELETE FROM id")
    suspend fun deleteIDData()
}

@Dao
interface TestDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTest(idDta: RoomTest)

    @Query("SELECT * FROM test")
    suspend fun getAllUsers(): List<RoomTest>

    @Update
    suspend fun updateTest(data: RoomTest)
}