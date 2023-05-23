package com.knockknock.server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class MailConfig(
        @Value("\${spring.mail.port}") val port: Long,
        @Value("\${spring.mail.username}") val username: String,
        @Value("\${spring.mail.password}") val password: String
) {

    @Bean
    fun javaMailSender(): JavaMailSenderImpl {
        var javaMailSender = JavaMailSenderImpl()

        javaMailSender.defaultEncoding = "UTF-8"
        javaMailSender.port = port.toInt()
        javaMailSender.protocol = "smtp"
        javaMailSender.javaMailProperties.setProperty("mail.smtp.starttls.enable", "true")
        javaMailSender.username = username
        javaMailSender.password = password

        return javaMailSender
    }
}