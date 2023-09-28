package com.example.authentication.authentication_jwt.verification

import com.example.authentication.authentication_jwt.config.exception.EntityNotFoundException
import com.example.authentication.authentication_jwt.domain.user.Contact
import com.example.authentication.authentication_jwt.domain.user.Email
import com.example.authentication.authentication_jwt.domain.verification.*
import com.example.authentication.authentication_jwt.domain.verification.exception.VerificationExpiredException
import com.example.authentication.authentication_jwt.domain.verification.exception.VerificationInvalidException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.Mockito.times
import java.sql.Timestamp
import java.time.Instant

class VerificationTest: MockTest() {
    private val verificationRepository: VerificationRepository = Mockito.mock(VerificationRepository::class.java)
    private val emailService: EmailService = Mockito.mock(EmailService::class.java)
    private val smsService: MessengerService = Mockito.mock(MessengerService::class.java)

    private lateinit var verificationService: VerificationService

    @BeforeEach
    fun setUp() {
        this.verificationService = VerificationService(
            verificationRepository= this.verificationRepository,
            emailService= this.emailService,
            messengerService = this.smsService
        )
    }

    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)



    @Test
    @DisplayName("인증코드 인증 성공 테스트")
    fun verifyVerificationCodeSuccess() {
        //given
        val contact: Contact = Email.validEmail("davidolleh@naver.com")
        val verificationCode: VerificationCode = VerificationCode(verificationNumber = "123456")
        val verification: Verification = Verification(contact = contact, verificationCode= verificationCode)


        doReturn(verification).`when`(verificationRepository).findByContact(any(String::class.java))

        //when
        verificationService.verifyVerificationCode(inputVerificationCode = verificationCode, contact = contact)

        //then
        Assertions.assertThat(true).isEqualTo(true)

        // verify
        verify(verificationRepository, times(1)).findByContact(any(String::class.java))
        verify(verificationRepository, times(1)).deleteByContact(any(String::class.java))
    }



    @Test
    @DisplayName("인증코드 불일치로 인한 인증 실패")
    fun verifyVerificationCodeFailByDifferentCode() {
        //given
        val contact: Contact = Email.validEmail("davidolleh1@naver.com")
        val verificationCode: VerificationCode = VerificationCode(verificationNumber = "123456")
        val verification: Verification = Verification(contact = contact, verificationCode= verificationCode)


        doReturn(
            Verification(
                contact=contact,
                verificationCode = VerificationCode("654321"),
                expiration = verification.expiration
            )).`when`(verificationRepository).findByContact(any(String::class.java))

        //when
        val result = assertThrows(VerificationInvalidException::class.java) {
            verificationService.verifyVerificationCode(contact = contact, inputVerificationCode = verificationCode)
        }

        //then
        Assertions.assertThat(result::class.java).isEqualTo(VerificationInvalidException::class.java)

        // verify
        verify(verificationRepository, times(1)).findByContact(any(String::class.java))
    }

    @Test
    @DisplayName("인증코드 만료로 인한 인증 실패")
    fun verifyVerificationCodeFailByExpired() {
        //given
        val contact: Contact = Email.validEmail("davidolleh1@naver.com")
        val verificationCode: VerificationCode = VerificationCode(verificationNumber = "123456")

        doReturn(
            Verification(
                contact=contact,
                verificationCode = verificationCode,
                expiration = Timestamp.from(Instant.now())
            )).`when`(verificationRepository).findByContact(any(String::class.java))

        //when
        val result = assertThrows(VerificationExpiredException::class.java) {
            verificationService.verifyVerificationCode(contact = contact, inputVerificationCode = verificationCode)
        }

        //then
        Assertions.assertThat(result::class.java).isEqualTo(VerificationExpiredException::class.java)

        // verify
        verify(verificationRepository, times(1)).findByContact(any(String::class.java))
        verify(verificationRepository, times(1)).deleteByContact(any(String::class.java))
    }

    @Test
    @DisplayName("해당 contact 주소가 db에 없는 오류")
    fun verifyVerificationCodeFailByNotFoundException() {
        //given
        val contact: Contact = Email.validEmail("davidolleh1@naver.com")
        val verificationCode: VerificationCode = VerificationCode(verificationNumber = "123456")

        doReturn(null).`when`(verificationRepository).findByContact(any(String::class.java))

        //when
        val result = assertThrows(EntityNotFoundException::class.java) {
            verificationService.verifyVerificationCode(contact = contact, inputVerificationCode = verificationCode)
        }

        //then
        Assertions.assertThat(result::class.java).isEqualTo(EntityNotFoundException::class.java)

        // verify
        verify(verificationRepository, times(1)).findByContact(any(String::class.java))
        verify(verificationRepository, times(0)).deleteByContact(any(String::class.java))
    }

}