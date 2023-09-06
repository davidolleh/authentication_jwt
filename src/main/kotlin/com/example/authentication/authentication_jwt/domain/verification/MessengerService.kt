package com.example.authentication.authentication_jwt.domain.verification

import net.nurigo.sdk.NurigoApp.initialize
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.response.SingleMessageSentResponse
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MessengerService @Autowired constructor(
    @Value("\${spring.sms.key}")
    private val coolSMSKey : String,
    @Value("\${spring.sms.secret-key}")
    private val coolSMSSecretKey: String,
    @Value("\${spring.sms.username}")
    private val coolSMSUserName: String,
    @Value("\${spring.sms.url}")
    private val coolSMSUrl: String
) {
    private val coolSMSMessageService: DefaultMessageService = initialize(coolSMSKey, coolSMSSecretKey, coolSMSUrl)

    private fun setMessage(verification: Verification): Message =
        Message(
            from = coolSMSUserName,
            to = verification.contact.readDestination(),
            text = "인증번호는 " + verification.verificationCode.verificationNumber + " 입니다"
        )

    fun sendMessage(verification: Verification): SingleMessageSentResponse? {
        val vMessage = this.setMessage(verification=verification)

        val response = coolSMSMessageService.sendOne(SingleMessageSendingRequest(vMessage))
        println(response)

        return response
    }
}