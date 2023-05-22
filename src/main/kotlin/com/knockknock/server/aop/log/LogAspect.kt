package com.knockknock.server.aop.log

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LogAspect {
    private val log = LoggerFactory.getLogger(javaClass)

    @Around("@annotation(LogExecutionTime)")
    @Throws(Throwable::class)
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis();

        var proceed = joinPoint.proceed();

        val executionTime = System.currentTimeMillis() - start;

        log.info("${joinPoint.signature} executed in ${executionTime}milliseconds")

        return proceed;
    }
}