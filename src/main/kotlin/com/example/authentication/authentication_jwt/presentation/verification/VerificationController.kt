package com.example.authentication.authentication_jwt.presentation.verification

import com.example.authentication.authentication_jwt.domain.user.Contact
import com.example.authentication.authentication_jwt.domain.verification.VerificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

class VerificationController @Autowired constructor(
    private val verificationService: VerificationService
) {
    @PostMapping()
    fun sendVerification(
        @RequestBody requestBody: Map<String, String>
    ) {
        var contact: Contact

//        when (requestBody["contact"]) {
//            contains
//        }
//
//
//        verificationService.sendVerificationToDestination(contact = requestBody["contact"])
    }
}