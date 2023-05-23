package com.knockknock.server.repository;

import com.knockknock.server.entity.account.AccountAuthentication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface AccountAuthenticationRepository : JpaRepository<AccountAuthentication, UUID> {


    fun countDistinctByPhoneNoAndExpireTimeGreaterThanEqual(phoneNo: String, expireTime: LocalDateTime): Long

}