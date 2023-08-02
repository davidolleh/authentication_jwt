package com.example.authentication.authentication_jwt.presentation.verification

import com.example.authentication.authentication_jwt.domain.user.Contact
import com.example.authentication.authentication_jwt.domain.user.Email
import com.example.authentication.authentication_jwt.domain.user.PhoneNumber
import com.example.authentication.authentication_jwt.domain.verification.VerificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class VerificationController @Autowired constructor(
    private val verificationService: VerificationService
) {
    @PostMapping("/verification")
    fun sendVerification(
        @RequestBody requestBody: VerificationRequestDto
    ) : Boolean {
        var contact: Contact = if (requestBody.phoneNumber != null) {
            PhoneNumber.validPhoneNumber(requestBody.phoneNumber)
        } else {
            Email.validEmail(requestBody.email!!)
        }

        return try {
            verificationService.sendVerificationToDestination(contact = contact)
            true
        } catch (e: Exception) {
            false
        }
    }
}