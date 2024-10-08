package com.example.authentication.authentication_jwt.domain.verification

import com.example.authentication.authentication_jwt.config.exception.EntityNotFoundException
import com.example.authentication.authentication_jwt.domain.user.Contact
import com.example.authentication.authentication_jwt.domain.user.Email
import com.example.authentication.authentication_jwt.domain.user.PhoneNumber
import com.example.authentication.authentication_jwt.domain.verification.exception.VerificationExpiredException
import com.example.authentication.authentication_jwt.domain.verification.exception.VerificationInvalidException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service




@Service
class VerificationService @Autowired constructor(
    private val verificationRepository: VerificationRepository,
    private val emailService: EmailService,
    private val messengerService: MessengerService
) {
    fun sendVerificationToDestination(contact: Contact) {
        val verification: Verification = this.createVerification(contact=contact)

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
        val vMessage = messengerService.sendMessage(verification=verification)
    }

    private fun sendVerificationToEmail(
        verification: Verification
    ) {
        val vMail = emailService.sendEmail(verification=verification)
    }

    private fun createVerificationCode(): VerificationCode {
        return VerificationCodeGenerator.createVerificationCode()
    }


    fun verifyVerificationCode(inputVerificationCode: VerificationCode, contact: Contact): Boolean {
        val verification: Verification = verificationRepository.findByContact(contact = contact.readDestination())
            ?: throw EntityNotFoundException()


        if (verification.isExpired()) {
            verificationRepository.deleteByContact(contact = contact.readDestination())
            throw VerificationExpiredException()
        }



        if (verification.verificationCode != inputVerificationCode) {
            // 인증 번호 재전송 하라 하거나 새 인증번호 다시 입력
            throw VerificationInvalidException()
        }

        verificationRepository.deleteByContact(contact = contact.readDestination())

        return true
    }

    private fun createVerification(contact: Contact): Verification {
        if (verificationRepository.findByContact(contact = contact.readDestination()) != null) {
            verificationRepository.deleteByContact(contact = contact.readDestination())
        }

        val verificationCode: VerificationCode = this.createVerificationCode()
        val verification: Verification = Verification(verificationCode=verificationCode, contact = contact)

        verificationRepository.save(verification)

        return verification
    }
}