package com.example.authentication.authentication_jwt.domain.verification

import com.example.authentication.authentication_jwt.domain.user.Contact
import java.sql.Timestamp
import java.time.Instant

data class Verification(
    var destination: Contact,
    var verificationCode: VerificationCode,
    var expiration: Timestamp = Timestamp.from(Instant.now())
) {
    fun isExpired() :Boolean = expiration.before(Timestamp.from(Instant.now()))
}
