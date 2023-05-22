package com.knockknock.server

import com.knockknock.server.entity.Account
import com.knockknock.server.service.AccountService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension::class)
@TestMethodOrder(OrderAnnotation::class)    // Equivalent with OrderAnnotation.class in java code.
class AccountAggregationServiceTests(
        @Autowired val accountService: AccountService
        ){

    @Test
    @Order(10)
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
    @Order(20)
    fun 이메일로_계정정보_찾기(){
        var email = "tjrkd222@gmail.com"
        val account: Account = accountService.getAccountByEmail(email);

        assertEquals(account.phoneNo, "010-2776-5098")
    }

    @Test
    @Order(30)
    fun 존재하는_이메일_중복확인(){
        var email = "tjrkd222@gmail.com"

        val existEmail = accountService.checkUniqueEmail(email);

        assertEquals(true, existEmail);
    }

    @Test
    @Order(31)
    fun 존재하지않는_이메일_중복확인(){
        var email = "tjrkd221@gmail.com"

        val existEmail = accountService.checkUniqueEmail(email);

        assertEquals(false, existEmail);
    }

    @Test
    @Order(40)
    fun 존재하는_닉네임_중복확인(){
        var nickname = "inseoking"

        val existNickname = accountService.checkUniqueNickname(nickname);

        assertEquals(true, existNickname);
    }

    @Test
    @Order(41)
    fun 존재하지않는_닉네임_중복확인(){
        var nickname = "inseoking2"

        val existNickname = accountService.checkUniqueNickname(nickname);

        assertEquals(false, existNickname);
    }
}