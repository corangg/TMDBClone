package com.example.tmdb.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdb.data.model.ID.IDData

@Dao
interface IDDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIDData(idDta: IDData)

    @Query("SELECT * FROM id")
    suspend fun getIDData(): IDData?

    @Query("DELETE FROM id")
    suspend fun deleteIDData()
}