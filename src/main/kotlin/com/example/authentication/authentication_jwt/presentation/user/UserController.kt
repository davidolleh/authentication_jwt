package com.example.authentication.authentication_jwt.presentation.user

import com.example.authentication.authentication_jwt.domain.user.UserService
import com.example.authentication.authentication_jwt.domain.verification.VerificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController @Autowired constructor(
    val userService: UserService,
    val verificationService: VerificationService
) {

    @PostMapping("/users/sign_up")
    fun signUp(
//        @RequestBody request: SignUpRequest
    ) {

    }


}