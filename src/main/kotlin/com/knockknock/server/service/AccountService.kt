package com.knockknock.server.service

import com.knockknock.server.aop.log.LogExecutionTime
import com.knockknock.server.entity.Account
import com.knockknock.server.repository.AccountRepository
import com.knockknock.server.utils.EncryptionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
        private val accountRepository: AccountRepository,
        private val passwordEncoder: PasswordEncoder
) {

    @LogExecutionTime
    fun saveAccount(account: Account): Account {
        val encryptedPassword = passwordEncoder.encode(account.password);
        account.password = encryptedPassword;

        return accountRepository.save(account)
    }

    fun checkUniqueEmail(email: String): Boolean {
        return accountRepository.existsByEmail(email)
    }

    fun getAccountByEmail(email: String): Account {
        return accountRepository.findByEmail(email)
    }

}