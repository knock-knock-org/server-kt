package com.knockknock.server.aop.exception

import com.knockknock.server.aop.log.ResCodeEnums
import java.lang.RuntimeException

class BaseException(
        baseResCode: ResCodeEnums = ResCodeEnums.BASE_ERROR
): RuntimeException() {
    val baseResCode = baseResCode
}