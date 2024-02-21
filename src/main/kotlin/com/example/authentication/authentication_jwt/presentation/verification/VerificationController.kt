package com.example.authentication.authentication_jwt.presentation.verification

import com.example.authentication.authentication_jwt.domain.user.Contact
import com.example.authentication.authentication_jwt.domain.user.Email
import com.example.authentication.authentication_jwt.domain.user.PhoneNumber
import com.example.authentication.authentication_jwt.domain.verification.VerificationCode
import com.example.authentication.authentication_jwt.domain.verification.VerificationService
import com.example.authentication.authentication_jwt.presentation.core.ApiResponse
import com.example.authentication.authentication_jwt.presentation.core.ApiResponse.Companion.success
import com.example.authentication.authentication_jwt.presentation.verification.dto.VerificationRequest
import com.example.authentication.authentication_jwt.presentation.verification.dto.VerificationResponse
import com.example.authentication.authentication_jwt.presentation.verification.dto.VerifyReqeust
import com.example.authentication.authentication_jwt.presentation.verification.dto.VerifyResponse
import com.example.authentication.authentication_jwt.security.JwtTokenProvider
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth/local/verification")
@RestController
class VerificationController @Autowired constructor(
    private val verificationService: VerificationService,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @PostMapping()
    fun sendVerification(
        @RequestBody requestBody: VerificationRequest
    ) : ApiResponse<VerificationResponse> {
        val contact: Contact = if (requestBody.phoneNumber != null) {
            PhoneNumber.validPhoneNumber(requestBody.phoneNumber)
        } else {
            Email.validEmail(requestBody.email!!)
        }

        return try {
            verificationService.sendVerificationToDestination(contact = contact)
            success(VerificationResponse(true))
        } catch (e: Exception) {
            error("abc")
        }
    }


    @DeleteMapping("/verify")
    fun verifyVerification(
        @Valid @RequestBody requestBody: VerifyReqeust
    ): ApiResponse<VerifyResponse> {
        val contact: Contact = if (requestBody.phoneNumber != null) {
            PhoneNumber.validPhoneNumber(requestBody.phoneNumber)
        } else {
            Email.validEmail(requestBody.email!!)
        }

        val success = verificationService.verifyVerificationCode(
            contact = contact,
            inputVerificationCode = VerificationCode(verificationNumber = requestBody.verificationCode)
        )

        val token = jwtTokenProvider.createToken(
            userPk = contact.readDestination(),
        )
        // 임시 토큰 줘야됨
        return success(VerifyResponse(token))
    }
}