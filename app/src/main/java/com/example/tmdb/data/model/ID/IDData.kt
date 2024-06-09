package com.example.tmdb.data.model.ID

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "id")
data class IDData(
    @PrimaryKey
    val id : String,
    val password : String
)
