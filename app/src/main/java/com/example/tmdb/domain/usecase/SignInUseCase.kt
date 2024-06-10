package com.example.tmdb.domain.usecase

import com.example.tmdb.domain.repository.AccountRepository

class SignInUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(id: String, password: String): String? {
        return accountRepository.signIn(id, password)
    }
}