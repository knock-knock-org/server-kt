package com.knockknock.server.serviceAggregation

import com.knockknock.server.aop.log.CustomLog
import com.knockknock.server.aop.log.ResCodeEnums
import com.knockknock.server.entity.account.Account
import com.knockknock.server.entity.account.AccountAuthentication
import com.knockknock.server.entity.account.AuthType
import com.knockknock.server.service.AccountAuthenticationService
import com.knockknock.server.service.AccountService
import com.knockknock.server.utils.ENCRYPTED_STRING
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountAggregationService (
        private val accountService: AccountService,
        private val authenticationService: AccountAuthenticationService,
        @Value("\${expire-time.auth}") var expireTime: Long
        ){



//    @LogExecutionTime
    @CustomLog(logLevel = LogLevel.INFO, resCode = ResCodeEnums.INFO_ACCOUNT_001)
    fun saveAccount(account: Account): ResponseEntity<Account> {
        if(account.password == null) throw NullPointerException("비밀번호가 존재하지 않습니다.")

        val result = accountService.saveAccount(account)
        result.password = ENCRYPTED_STRING

        return ResponseEntity(result, ResCodeEnums.INFO_ACCOUNT_001.status)
    }

    fun checkUniqueEmail(email: String): ResponseEntity<Boolean> = ResponseEntity(accountService.checkUniqueEmail(email), HttpStatus.OK)

    fun checkUniqueNickname(nickname: String): ResponseEntity<Boolean> = ResponseEntity(accountService.checkUniqueEmail(nickname), HttpStatus.OK)

    /**
     * TODO: async로 전송해야 함. => Boolean 보내는 것말고 다른 방법으로 변경해야 할듯. 다른 사이트 확인해보자.
     * */
    @Transactional
    fun sendAuthCode(phoneNo: String): ResponseEntity<Boolean> {

        //10분 이내 5번 이상 요청했으면 block(이번 요청 포함)
        var recentRequestCnt = authenticationService.checkTooManyRequestSendingSMS(phoneNo)
        if(recentRequestCnt >= 4) return ResponseEntity(false, HttpStatus.LOCKED)

        //업로드
        val code = authenticationService.makeAuthCode()
        val smsAuthentication: AccountAuthentication = AccountAuthentication(
                authType = AuthType.SMS,
                phoneNo = phoneNo,
                code = code,
                expireTime = expireTime
        )
        authenticationService.saveAuthentication(smsAuthentication)

        //문자보내기
        authenticationService.sendPhoneAuthCode(phoneNo, code)

        return ResponseEntity(true, HttpStatus.OK)
    }
}