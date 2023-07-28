package com.example.authentication.authentication_jwt.presentation.verification

import com.example.authentication.authentication_jwt.domain.user.Contact
import com.example.authentication.authentication_jwt.domain.user.Email
import com.example.authentication.authentication_jwt.domain.user.PhoneNumber
import com.example.authentication.authentication_jwt.domain.verification.VerificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

class VerificationController @Autowired constructor(
    private val verificationService: VerificationService
) {
    @PostMapping("/verification")
    fun sendVerification(
        @RequestBody requestBody: Map<String, String>
    ) {
        var contact: Contact

        var requestKeys: Set<String> = requestBody.keys

        contact = if (requestKeys.contains("phone_number")) {
            PhoneNumber.validPhoneNumber(requestBody["phone_number"]!!)
        } else {
            Email.validEmail(requestBody["email"]!!)
        }

        verificationService.sendVerificationToDestination(contact = contact)
    }
}