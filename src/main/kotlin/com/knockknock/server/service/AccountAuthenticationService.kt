package com.knockknock.server.service

import com.knockknock.server.utils.validatePhoneNoFormat
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import net.nurigo.sdk.NurigoApp.initialize
import java.util.*

@Service
class AccountAuthenticationService (
        @Value("\${coolsms.api}") val apiKey: String,
        @Value("\${coolsms.api-secret}") val apiSecretKEy: String,
        @Value("\${coolsms.url}") val url: String,
        ){

        val smsService: DefaultMessageService = initialize(
                apiKey, apiSecretKEy, url
        )


        fun sendCode(phoneNo: String) {
                if(!validatePhoneNoFormat(phoneNo)) throw IllegalFormatException()
        }
}