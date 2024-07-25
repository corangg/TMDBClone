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
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var age: Int = 0,
    var email: String = "",
    var address: String = ""
) : RealmObject()

@Entity(tableName = "test")
data class RoomTest(
    @PrimaryKey val id: Int = 0,
    val name: String = "",
    val age: Int = 0,
    val email: String = "",
    val address: String = ""
)