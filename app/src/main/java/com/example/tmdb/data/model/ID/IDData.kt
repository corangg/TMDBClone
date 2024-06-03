package com.example.tmdb.data.model.ID

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ID")
data class IDData(
    @PrimaryKey
    val ID : String,
    val Password : String
)
