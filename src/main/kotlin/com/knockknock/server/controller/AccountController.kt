package com.knockknock.server.controller

import com.knockknock.server.serviceAggregation.AccountAggregationService
import com.knockknock.server.entity.Account
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(
        private val accountAggregationService: AccountAggregationService
){

    @PostMapping
    fun signUp(@RequestBody account: Account): ResponseEntity<Account> {
        return accountAggregationService.saveAccount(account);
    }
}