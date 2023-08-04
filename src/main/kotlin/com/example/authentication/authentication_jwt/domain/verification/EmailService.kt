package com.example.authentication.authentication_jwt.domain.verification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
class EmailService  @Autowired constructor(
    @Value("\${spring.mail.host}")
    private val smtpHostValue: String,
    @Value("\${spring.mail.port}")
    private val smtpPortValue: String,
    @Value("\${spring.mail.username}")
    private val smtpUserName: String,
    @Value("\${spring.mail.password}")
    private val smtpPw: String,
    @Value("\${spring.mail.properties.mail.mime.chartset}")
    private val smtpCharset: String,
) {

    private val smtpHostKey: String = "mail.smtp.host"
    private val smtpPortKey: String = "mail.smtp.port"
    private val smtpAuthKey: String = "mail.smtp.auth"
    private val smtpTlsKey: String = "mail.smtp.starttls.enable"

    private val props = System.getProperties()
    private var session: Session? = null



    init {

        this.createProperties()
        session = Session.getDefaultInstance(
            props,
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(smtpUserName, smtpPw)
                }
            }
        )

    }

    private fun createProperties() {
        props.put(smtpHostKey, smtpHostValue)
        props.put(smtpPortKey, smtpPortValue)
        props.put(smtpAuthKey, "true")
        props.put(smtpTlsKey, "true")
    }

    // 이것이 정확하게 무엇을 return 하는지 알아야 할 듯
    // object keyword의 효과를 정확하게 알자
    // object keyword 이용하면 이거 init 안에서 만들 수 있음
    private fun createAuthenticator() {
        object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(smtpUserName, smtpPw)
            }
        }
    }

    fun setEmail(verification: Verification): MimeMessage {

        try {
            val message: MimeMessage = MimeMessage(session)
            message.setFrom(InternetAddress(smtpUserName))

            // 받는 이메일
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(verification.contact.readDestination())
            )

            message.setSubject(
                "환영합니다",
//                smtpCharset
                "UTF-8"
            )
            message.setText(
                "인증번호는 " + verification.verificationCode.verificationNumber + " 입니다",
//                smtpCharset
                "UTF-8"
            )

            return message
        } catch (e: Exception) {
            throw Exception()
        }

    }
}