package com.knockknock.server.entity.account

import com.knockknock.server.entity.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.time.LocalDateTime

@Entity
class AccountAuthentication(
        authType: AuthType,
        code: String,
        phoneNo: String? = null,
        email: String? = null,
        expireTime: Long
): PrimaryKeyEntity() {

    @Column(nullable = false)
    var authType: AuthType = authType

    @Column(nullable = false)
    var code: String = code

    @Column(nullable = true)
    var phoneNo: String? = phoneNo

    @Column(nullable = true)
    var email: String? = email

    @Column(nullable = false)
    var expireTime: LocalDateTime = LocalDateTime.now().plusSeconds(expireTime / 1000)

}

enum class AuthType {
    EMAIL, SMS
}