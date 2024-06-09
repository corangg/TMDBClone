package com.example.tmdb.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tmdb.data.model.ID.IDData

@Database(entities = [IDData::class], version = 2, exportSchema = false)
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
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE ID RENAME TO ID_temp")
                db.execSQL(
                    """
                    CREATE TABLE id (
                        id TEXT PRIMARY KEY NOT NULL,
                        password TEXT NOT NULL
                    )
                """.trimIndent()
                )
                db.execSQL(
                    """
                    INSERT INTO id (id, password)
                    SELECT ID, Password FROM ID_temp
                """.trimIndent()
                )
                db.execSQL("DROP TABLE ID_temp")
            }
        }
    }
}