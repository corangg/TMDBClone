package com.example.tmdb.data.source.remot

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.tmdb.data.model.ID.IDData
import com.example.tmdb.data.model.ID.IDSQL

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_ID = "id"
        private const val COLUMN_ID = "id"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_ID (
                $COLUMN_ID TEXT PRIMARY KEY,
                $COLUMN_PASSWORD TEXT
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ID")
        onCreate(db)
    }
}

class IDSQlDao(context: Context) {
    private val dbHelper: MyDatabaseHelper = MyDatabaseHelper(context)

    fun insertIDData(idData: IDSQL) {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("id", idData.id)
            put("password", idData.password)
        }
        db.insertWithOnConflict("id", null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    fun getIDData(): IDData? {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        var idData: IDData? = null
        var cursor: Cursor? = null
        try {
            cursor = db.query("idaa", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                val id = cursor.getString(cursor.getColumnIndexOrThrow("id"))
                val password = cursor.getString(cursor.getColumnIndexOrThrow("password"))
                idData = IDData(id, password)
            }
        } catch (e: SQLiteException) {
            Log.e("IDSQL", "Error querying table: ${e.message}")
        } finally {
            cursor?.close()
            db.close()
        }
        return idData
    }

    fun deleteIDData() {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        db.delete("id", null, null)
        db.close()
    }
}