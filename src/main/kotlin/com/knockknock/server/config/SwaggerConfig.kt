package com.knockknock.server.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@Slf4j
class SwaggerConfig {

    val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun openAPI(): OpenAPI {

        var config = OpenAPI()

        var info = Info()
        info.title = "Knock Knock API Document"
        info.version = "0.3"

        config.info = info

        return config
    }
}