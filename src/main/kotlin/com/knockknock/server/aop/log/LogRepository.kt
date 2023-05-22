package com.knockknock.server.aop.log;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LogRepository : JpaRepository<Log, UUID> {
}