package com.example.tmdb.domain.usecase

import com.example.tmdb.data.model.Result
import com.example.tmdb.domain.repository.AccountRepository

class GetMyWatchListUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(): List<Result>? {
        return accountRepository.getMyWatchList()
    }
}