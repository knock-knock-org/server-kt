package com.knockknock.server.aop.log

import org.springframework.boot.logging.LogLevel

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class CustomLog (
        val logLevel: LogLevel,
        val resCode: ResCodeEnums
)
