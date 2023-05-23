package com.knockknock.server.aop.log

import org.springframework.http.HttpStatus

enum class ResCodeEnums(val msg: String, val status: HttpStatus) {
    INFO_ACCOUNT_001("계정이 생성되었습니다.", HttpStatus.CREATED)
}