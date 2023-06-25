package com.knockknock.server.aop.log

import org.springframework.http.HttpStatus

enum class ResCodeEnums(val msg: String, val status: HttpStatus) {
    BASE_ERROR("서버 오류입니다. 관리자에게 문의바랍니다.", HttpStatus.BAD_REQUEST),
    INFO_ACCOUNT_001("계정이 생성되었습니다.", HttpStatus.CREATED)
}