package com.knockknock.server.aop.log

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Aspect
@Component
class LogAspect (
        private val logService: LogService
        ){
    private val log = LoggerFactory.getLogger(javaClass)

    @Around("@annotation(LogExecutionTime)")
    @Throws(Throwable::class)
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis();

        var proceed = joinPoint.proceed()

        val executionTime = System.currentTimeMillis() - start;

        log.info("${joinPoint.signature} executed in ${executionTime}milliseconds")

        return proceed
    }

    @Around("@annotation(CustomLog)")
    @Throws(Throwable::class)
    fun customLog(joinPoint: ProceedingJoinPoint): Any? {
        try {
            var proceed = joinPoint.proceed()

            val methodSignature = joinPoint.signature as MethodSignature;
            val custom = methodSignature.method.getAnnotation(CustomLog::class.java);

            var customLog = Log(
                    level = custom.logLevel.name,
                    code = custom.resCode.name,
                    message = custom.resCode.msg
            )

            log.info("${customLog.code}: ${customLog.message}")
            logService.appendLogs(customLog)

            return proceed

        } catch (e: Exception) {
            var customLog = Log(
                    level = LogLevel.ERROR.name,
                    code = e::class.toString(),
                    message = e.message?: "Unexpected Error"
            )

            //TODO: 에러 시 interceptor 처리 필요.
            log.info("${customLog.code}: ${customLog.message}")
            logService.appendLogs(customLog)
            return null
        }

    }
}
