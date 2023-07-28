package com.example.authentication.authentication_jwt.domain.verification

import com.example.authentication.authentication_jwt.data.VerificationRepository
import com.example.authentication.authentication_jwt.domain.user.Contact
import com.example.authentication.authentication_jwt.domain.user.Email
import com.example.authentication.authentication_jwt.domain.user.PhoneNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException
import java.util.Properties
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
class VerificationService @Autowired constructor(
    val verificationRepository: VerificationRepository

) {
    fun sendVerificationToDestination(contact: Contact) {
        val verificationCode: VerificationCode = this.createVerificationCode()
        val verification: Verification = this.createVerification(verificationCode=verificationCode, contact=contact)

        when (verification.destination) {
            is PhoneNumber -> sendVerificationToSms(
//                phoneNumber = verification.destination as PhoneNumber,
//                countryCode = "82",
                verification=verification
            )
            is Email -> sendVerificationToEmail(
                verification=verification
            )
        }
    }

    private fun sendVerificationToSms(
//        phoneNumber: PhoneNumber,
//        countryCode: String,
        verification: Verification
    ) {
    }

    private fun sendVerificationToEmail(
        verification: Verification
    ) {
        val props: Properties = Properties()
        val session: Session = Session.getDefaultInstance(props, null)

        try {
            val msg =  MimeMessage(session)
            msg.setFrom(InternetAddress("asherolleh@gmail.com","황승준 회원가입 인증번호 입니다"))
//            msg.addRecipient(Message.RecipientType.TO, InternetAddress((verification.destination as Contact.Email).email))
            msg.addRecipient(Message.RecipientType.TO, InternetAddress(verification.destination.information))
            msg.setSubject("Hello world!")
            msg.setText(verification.verificationCode.verificationNumber)

            Transport.send(msg)

        } catch (e: AddressException) {

        } catch (e: MessagingException) {

        } catch (e: UnsupportedEncodingException) {

        }

    }

    private fun createVerificationCode(): VerificationCode {
        return VerificationCodeGenerator.createVerificationCode()
    }

    fun verifyVerificationCode() {


    }

    private fun createVerification(verificationCode: VerificationCode, contact: Contact): Verification {
        val verification: Verification = Verification(verificationCode=verificationCode, destination = contact)

        verificationRepository.save(verification)

        return verification
    }
}