package com.knockknock.server.service

import com.knockknock.server.entity.account.AccountAuthentication
import com.knockknock.server.repository.AccountAuthenticationRepository
import com.knockknock.server.utils.convertPhoneNoWithoutDash
import com.knockknock.server.utils.validatePhoneNoFormat
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import net.nurigo.sdk.NurigoApp.initialize
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.response.SingleMessageSentResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMailMessage
import java.time.LocalDateTime
import java.util.*

@Service
class AccountAuthenticationService (
        @Value("\${coolsms.api}") val apiKey: String,
        @Value("\${coolsms.api-secret}") val apiSecretKEy: String,
        @Value("\${coolsms.url}") val url: String,
        private var javaMailSender: JavaMailSender,
        private val authenticationRepository: AccountAuthenticationRepository
        ){

        val logger: Logger = LoggerFactory.getLogger(javaClass);

        val smsService: DefaultMessageService = initialize(
                apiKey, apiSecretKEy, url
        )

        fun sendPhoneAuthCode(phoneNo: String, authCode: String): SingleMessageSentResponse? {
                if(!validatePhoneNoFormat(phoneNo)) throw IllegalArgumentException(phoneNo)

                val phoneNoWithoutDash = convertPhoneNoWithoutDash(phoneNo)

                val message = Message(
                        from = "01027765098",
                        to = phoneNoWithoutDash,
                        text = "[KnockKnock] 인증번호는 ${authCode} 입니다."
                        // country = "국가번호"
                )
                val response = smsService.sendOne(SingleMessageSendingRequest(message))

                logger.info("$response")

                return response
        }

        fun makeAuthCode(): String {
                var random = kotlin.random.Random.nextDouble(0.0, 1.0)
                random *= 1000000
                random = kotlin.math.ceil(random)

                return random.toInt().toString()
        }

        fun checkTooManyRequestSendingSMS(phoneNo: String): Long = authenticationRepository.countDistinctByPhoneNoAndExpireTimeGreaterThanEqual(phoneNo, LocalDateTime.now().minusMinutes(5))

        fun saveAuthentication(auth: AccountAuthentication): AccountAuthentication = authenticationRepository.save(auth);

        fun sendEmailAuthCode() {
                var message = javaMailSender.createMimeMessage()

        }
}