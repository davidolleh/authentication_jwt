package com.example.authentication.authentication_jwt.domain.verification

import com.example.authentication.authentication_jwt.domain.user.Contact
import com.example.authentication.authentication_jwt.domain.user.Email
import com.example.authentication.authentication_jwt.domain.user.PhoneNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.mail.*




@Service
class VerificationService @Autowired constructor(
    val verificationRepository: VerificationRepository,
    val emailService: EmailService
) {
    fun sendVerificationToDestination(contact: Contact) {
        val verificationCode: VerificationCode = this.createVerificationCode()

        val verification: Verification = this.createVerification(verificationCode=verificationCode, contact=contact)

        when (verification.contact) {
            is PhoneNumber -> sendVerificationToSms(
                verification=verification
            )
            is Email -> sendVerificationToEmail(
                verification=verification
            )
        }
    }

    private fun sendVerificationToSms(
        verification: Verification
    ) {
    }

    private fun sendVerificationToEmail(
        verification: Verification
    ) {
        val message = emailService.setEmail(verification=verification)

        Transport.send(message)
    }

    private fun createVerificationCode(): VerificationCode {
        return VerificationCodeGenerator.createVerificationCode()
    }


        fun verifyVerificationCode() {


    }

    private fun createVerification(verificationCode: VerificationCode, contact: Contact): Verification {
        val verification: Verification = Verification(verificationCode=verificationCode, contact = contact)

        verificationRepository.save(verification)

        return verification
    }
}