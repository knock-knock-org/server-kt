package com.knockknock.server.controller

import com.knockknock.server.serviceAggregation.AccountAggregationService
import com.knockknock.server.entity.Account
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(
        private val accountAggregationService: AccountAggregationService
){

    @PostMapping("/sign-up")
    fun signUp(@RequestBody account: Account): ResponseEntity<Account> {

        return accountAggregationService.saveAccount(account)
    }

    @GetMapping("/check-mail/{email}")
    fun checkMailDuplicate(@PathVariable email: String): ResponseEntity<Boolean>{
        return accountAggregationService.checkUniqueEmail(email)
    }
}