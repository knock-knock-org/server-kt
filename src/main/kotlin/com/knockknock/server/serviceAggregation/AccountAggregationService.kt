package com.knockknock.server.serviceAggregation

import com.knockknock.server.entity.Account
import com.knockknock.server.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccountAggregationService (
        private val accountService: AccountService
        ){

    fun saveAccount(account: Account): ResponseEntity<Account> {
        val result = accountService.saveAccount(account)

        return ResponseEntity(result, HttpStatus.CREATED)
    }
}