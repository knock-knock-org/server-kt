package com.knockknock.server.aop.exception

import com.knockknock.server.aop.log.ResCodeEnums
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(javaClass);


    @ExceptionHandler(BaseException::class)
    protected fun handleBaseException(e: BaseException): ResponseEntity<ResCodeEnums> {

        logger.info("handleBaseException:: Is it Run?")

        return ResponseEntity(e.baseResCode, e.baseResCode.status)
    }
}