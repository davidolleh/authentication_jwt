package com.example.authentication.authentication_jwt.presentation.verification.dto

data class VerificationRequest(
    val phoneNumber: String?,
    val email: String?
) {

}

data class VerificationResponse(
    val isSend: Boolean
)