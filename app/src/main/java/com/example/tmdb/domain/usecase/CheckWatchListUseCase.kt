package com.example.tmdb.domain.usecase

import com.example.tmdb.domain.repository.AccountRepository

class CheckWatchListUseCase(private val accountRepository: AccountRepository) {
    fun execute(id: Int): Boolean {
        return accountRepository.checkWatchList(id)
    }
}