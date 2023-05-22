package com.knockknock.server.aop.log

import com.knockknock.server.entity.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.RequiredArgsConstructor
import java.time.LocalDateTime

@Entity
@Table(name = "log")
@RequiredArgsConstructor
open class Log(
        level: String,
        code: String,
        message: String
) : PrimaryKeyEntity(){

    @Column(nullable = false)
    open var env: String = "dev"

    @Column(nullable = false)
    open val level: String = level

    @Column(nullable = false)
    open val timestamp: LocalDateTime = LocalDateTime.now();

    @Column(nullable = false)
    open var code: String = code

    @Column(nullable = false)
    open var message: String = message
}