package com.knockknock.server.service

import com.knockknock.server.entity.Account
import com.knockknock.server.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService(
        private val accountRepository: AccountRepository
) {

    fun saveAccount(account: Account): Account = accountRepository.save(account)

}