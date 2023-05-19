package com.knockknock.server

import com.knockknock.server.entity.Account
import com.knockknock.server.service.AccountService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountAggregationServiceTest @Autowired constructor(
         val accountService: AccountService
        ){

    @Test
    @DisplayName("Create Account")
    fun 계정_생성() {
        val account: Account = Account(
                email = "tjrkd222@gmail.com",
                password = "1234",
                nickname = "inseoking",
                phoneNo = "010-2776-5098",
                gender = null,
                birthYear = null,
                hashtagApperanceYn = null,
                lastLoginTimestamp = null,
                job = null,
        )

        val savedAccount: Account = accountService.saveAccount(account)

        assertEquals(account, savedAccount)
    }

    @Test
    fun 이메일로_계정정보_찾기(){
        var email = "tjrkd222@gmail.com"
        val account: Account = accountService.getAccountByEmail(email);

        assertEquals(account.phoneNo, "010-2776-5098")
    }

    @Test
    fun 이메일_중복확인(){
        var email = "tjrkd222@gmail.com"

        val existEmail = accountService.checkUniqueEmail(email);

        assertEquals(true, existEmail);
    }
}