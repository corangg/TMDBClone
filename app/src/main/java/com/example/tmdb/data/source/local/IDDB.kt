package com.example.tmdb.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.model.ID.RoomTest


@Database(entities = [IDData::class], version = 1)
abstract class IDDB : RoomDatabase() {
    abstract fun idDao(): IDDao

    companion object {
        @Volatile
        private var INSTANCE: IDDB? = null

        fun getDatabase(context: Context): IDDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IDDB::class.java,
                    "id_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

@Database(entities = [RoomTest::class], version = 1)
abstract class TestDB : RoomDatabase() {
    abstract fun testDao(): TestDao

    companion object {
        @Volatile
        private var INSTANCE: TestDB? = null

        fun getDatabase(context: Context): TestDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TestDB::class.java,
                    "id_dat"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}