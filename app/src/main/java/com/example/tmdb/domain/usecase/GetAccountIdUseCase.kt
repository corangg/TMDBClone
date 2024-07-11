package com.example.tmdb.domain.usecase

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.data.model.Result
import com.example.tmdb.data.model.account.AccountDetailsResponse
import com.example.tmdb.data.source.remot.retrofit.TMDBRetrofit
import com.example.tmdb.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAccountIdUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(sessionId: String): AccountDetailsResponse? {
        return accountRepository.getAccountId(sessionId)
    }
}


class FlowBuilder@Inject constructor() {
    suspend operator fun invoke(collect: String): Flow<List<Result>?> = flow {
        val list = TMDBRetrofit.fetchNowPlayingMovies() ?: return@flow
        Log.d("flow", collect)
        emit(list)
    }.catch { e -> println(e) }
}

/*class SharedFlowBuilder @Inject constructor() {
    suspend operator fun invoke() {
        try {
            val movies = TMDBRetrofit.fetchNowPlayingMovies() ?: return
            _moviesStateFlow.value = movies
        } catch (e: Exception) {
            // Handle the error
        }
    }
}*/
