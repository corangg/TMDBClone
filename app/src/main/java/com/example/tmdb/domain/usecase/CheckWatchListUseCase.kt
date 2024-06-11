package com.example.tmdb.domain.usecase

import com.example.tmdb.data.model.Result
import com.example.tmdb.domain.repository.AccountRepository

class CheckWatchListUseCase(private val accountRepository: AccountRepository) {
    fun execute(id: Int, myWatchList: List<Result>): Boolean {
        return accountRepository.checkWatchList(id, myWatchList)
    }
}