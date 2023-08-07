package com.example.authentication.authentication_jwt.domain.verification

import com.example.authentication.authentication_jwt.domain.user.Contact
import java.sql.Timestamp
import java.time.Instant

data class Verification(
    var contact: Contact,

    var verificationCode: VerificationCode,

    var expiration: Timestamp = Timestamp.from(Instant.now().plusSeconds(180))
) {
    fun isExpired() :Boolean = expiration.before(Timestamp.from(Instant.now()))
}
