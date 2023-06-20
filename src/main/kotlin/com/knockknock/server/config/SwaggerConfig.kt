package com.knockknock.server.config

import io.swagger.v3.oas.models.Components
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

    private fun apiInfo() = Info()
            .title("KnockKnock API Docs")
            .description("넉넉 API 문서")
            .version("0.0.3")

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
            .components(Components())
            .info(apiInfo())
}