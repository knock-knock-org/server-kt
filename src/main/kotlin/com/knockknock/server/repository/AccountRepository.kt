package com.knockknock.server.repository;

import com.knockknock.server.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {

    fun existsByEmail(email: String): Boolean


    fun findByEmail(email: String): Account


    fun existsByNickname(nickname: String): Boolean

}