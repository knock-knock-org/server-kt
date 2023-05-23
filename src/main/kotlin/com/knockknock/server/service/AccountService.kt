package com.knockknock.server.service

import com.knockknock.server.entity.account.Account
import com.knockknock.server.repository.AccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
        private val accountRepository: AccountRepository,
        private val passwordEncoder: PasswordEncoder
) {

    fun saveAccount(account: Account): Account {
        val encryptedPassword = passwordEncoder.encode(account.password);
        account.password = encryptedPassword;

        return accountRepository.save(account)
    }

    fun checkUniqueEmail(email: String): Boolean = accountRepository.existsByEmail(email)

    fun checkUniqueNickname(nickname: String): Boolean = accountRepository.existsByNickname(nickname)

    fun getAccountByEmail(email: String): Account {
        return accountRepository.findByEmail(email)
    }

}