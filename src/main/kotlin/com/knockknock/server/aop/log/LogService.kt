package com.knockknock.server.aop.log

import org.springframework.stereotype.Service

@Service
class LogService (
        private val logRepository: LogRepository
        ) {

    fun appendLogs(log: Log): Log = logRepository.save(log)

}