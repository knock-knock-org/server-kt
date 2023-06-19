package com.knockknock.server.repository;

import com.knockknock.server.entity.account.AccountAuthentication
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface AccountAuthenticationRepository : JpaRepository<AccountAuthentication, UUID> {


    fun countDistinctByPhoneNoAndExpireTimeGreaterThanEqual(phoneNo: String, expireTime: LocalDateTime): Long

    @Query(value = "select auth from AccountAuthentication as auth where auth.email = ?1 order by auth.expireTime desc")
    fun findAuthCodeByEmail(email: String, pageble: Pageable): List<AccountAuthentication>

    fun findAuthCodeByEmail(email: String): AccountAuthentication {
        var result =  findAuthCodeByEmail(email, PageRequest.of(0,1));
        return result[0]
    }
}
