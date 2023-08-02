package com.example.authentication.authentication_jwt.presentation.verification

data class VerificationRequestDto(
    val phoneNumber: String?,
    val email: String?
) {

}