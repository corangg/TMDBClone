package com.example.tmdb.util

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

object LogUtil {
    fun logError(tag: String, message: String) {
        Log.e(tag, "Error: $message")
    }

    fun logException(tag: String, exception: Exception) {
        Log.e(tag, "Exception: ${exception.message}", exception)
    }
}