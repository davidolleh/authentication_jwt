package com.example.authentication.authentication_jwt.domain.verification

import java.security.SecureRandom

abstract class VerificationCodeGenerator {
    companion object {
        private val secureRandom = SecureRandom.getInstanceStrong()

        fun createVerificationCode(): VerificationCode {
            val digits = mutableListOf<Int>()
            for (i in 0 until 6) {
                digits.add(secureRandom.nextInt(10))
            }

            return VerificationCode(digits.toString())
        }
    }
}