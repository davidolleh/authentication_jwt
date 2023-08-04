package com.example.authentication.authentication_jwt

import com.example.authentication.authentication_jwt.domain.verification.EmailService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthenticationJwtApplication

fun main(args: Array<String>) {
	runApplication<AuthenticationJwtApplication>(*args)
}
