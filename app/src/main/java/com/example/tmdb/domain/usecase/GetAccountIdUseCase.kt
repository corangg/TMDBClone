package com.example.tmdb.domain.usecase

import com.example.tmdb.data.model.account.AccountDetailsResponse
import com.example.tmdb.domain.repository.AccountRepository

class GetAccountIdUseCase(private val accountRepository: AccountRepository) {
    suspend fun execute(): AccountDetailsResponse? {
        return accountRepository.getAccountId()
    }
}