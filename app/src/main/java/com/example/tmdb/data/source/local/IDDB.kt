package com.example.tmdb.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdb.data.model.ID.IDData


@Database(entities = [IDData::class], version = 1, exportSchema = false)
abstract class IDDB: RoomDatabase() {
    abstract fun idDao(): IDDao

    companion object{
        @Volatile
        private var INSTANCE: IDDB? = null

        fun getDatabase(context: Context): IDDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IDDB::class.java,
                    "id_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}