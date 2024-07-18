package com.example.tmdb.data.model.ID

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.realm.RealmObject

@Entity(tableName = "id")
data class IDData(
    @PrimaryKey
    val id : String,
    val password : String
)

data class IDSQL(
    val id : String,
    val password : String
)

open class User(
    @PrimaryKey var id: Int = 0
) : RealmObject()