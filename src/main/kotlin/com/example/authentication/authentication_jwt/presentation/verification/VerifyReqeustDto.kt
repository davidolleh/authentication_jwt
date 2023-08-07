package com.example.authentication.authentication_jwt.presentation.verification

data class VerifyReqeustDto(
    val email: String?,
    val phoneNumber: String?,
    val verificationCode: String
) {
}