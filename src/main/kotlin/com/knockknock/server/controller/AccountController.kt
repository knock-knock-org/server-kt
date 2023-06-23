package com.knockknock.server.controller

import com.knockknock.server.serviceAggregation.AccountAggregationService
import com.knockknock.server.entity.account.Account
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
    fun signUp(@RequestBody account: Account): ResponseEntity<Account> = accountAggregationService.saveAccount(account)

    @GetMapping("/check/mail/{email}")
    fun checkMailDuplicate(@PathVariable email: String): ResponseEntity<Boolean> = accountAggregationService.checkUniqueEmail(email)

    @GetMapping("/check/nickname/{nickname}")
    fun checkNicknameDuplicate(@PathVariable nickname: String): ResponseEntity<Boolean> = accountAggregationService.checkUniqueNickname(nickname)

    @GetMapping("/send/{phoneNo}")
    fun sendAuthCodeToPhoneNo(@PathVariable phoneNo: String): ResponseEntity<Boolean> = accountAggregationService.sendAuthCode(phoneNo)
}