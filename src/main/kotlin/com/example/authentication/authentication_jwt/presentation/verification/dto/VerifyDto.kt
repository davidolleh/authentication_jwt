package com.example.authentication.authentication_jwt.presentation.verification.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class VerifyReqeust(
    val email: String?,

    val phoneNumber: String?,

    @field:NotBlank(message = "인증코드는 공백이 될 수 없습니다")
    @field:Size(min = 6, max = 6)
    val verificationCode: String
) {
}

data class VerifyResponse(
    val token: String
)