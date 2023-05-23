package com.knockknock.server.config

import com.knockknock.server.scheduling.LoggingTaskDecorator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.ThreadPoolExecutor

@Configuration
@EnableAsync
class AsyncConfig {

    @Bean
    fun taskExecutor(): TaskExecutor {
        val taskExecutor = ThreadPoolTaskExecutor()
        taskExecutor.corePoolSize = 10
        taskExecutor.queueCapacity = 50
        taskExecutor.maxPoolSize = 30
        taskExecutor.setTaskDecorator(LoggingTaskDecorator())
        taskExecutor.setRejectedExecutionHandler(ThreadPoolExecutor.AbortPolicy())

        return taskExecutor
    }
}