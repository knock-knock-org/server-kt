package com.knockknock.server.serviceAggregation

import com.knockknock.server.aop.log.CustomLog
import com.knockknock.server.aop.log.LogExecutionTime
import com.knockknock.server.aop.log.ResCodeEnums
import com.knockknock.server.entity.Account
import com.knockknock.server.service.AccountService
import com.knockknock.server.utils.ENCRYPTED_STRING
import org.apache.kafka.common.protocol.types.Field.Bool
import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccountAggregationService (
        private val accountService: AccountService
        ){

//    @LogExecutionTime
    @CustomLog(logLevel = LogLevel.INFO, resCode = ResCodeEnums.INFO_ACCOUNT_001)
    fun saveAccount(account: Account): ResponseEntity<Account> {
        if(account.password == null) throw NullPointerException("비밀번호가 존재하지 않습니다.")

        val result = accountService.saveAccount(account)
        result.password = ENCRYPTED_STRING

        return ResponseEntity(result, HttpStatus.CREATED)
    }

    fun checkUniqueEmail(email: String): ResponseEntity<Boolean> {
        return ResponseEntity(accountService.checkUniqueEmail(email), HttpStatus.OK)
    }
}