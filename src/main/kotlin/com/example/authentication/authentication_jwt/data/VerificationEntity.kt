package com.example.authentication.authentication_jwt.data

import com.example.authentication.authentication_jwt.domain.user.Email
import com.example.authentication.authentication_jwt.domain.user.PhoneNumber
import com.example.authentication.authentication_jwt.domain.verification.Verification
import com.example.authentication.authentication_jwt.domain.verification.VerificationCode
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.sql.Timestamp
import java.time.Instant

@Entity
class VerificationEntity(
    contact: String,
    verificationCode: String,
    expiration: Timestamp = Timestamp.from(Instant.now().plusSeconds(180))
) {
    @Id
    @Column(name="VERIFICATION_CONTACT", nullable = false, unique = true)
    var contact: String = contact

    @Id
    @Column(name="VERIFICATION_CODE", nullable = false, unique = false)
    var verificationCode: String = verificationCode

    @Column(name = "VERIFICATION_EXPIRATION", nullable = false)
    var expiration: Timestamp = expiration

    companion object {
        fun fromDomain(domainV: Verification): VerificationEntity {
            return VerificationEntity(
                contact = domainV.contact.readDestination(),
                verificationCode = domainV.verificationCode.verificationNumber,
                expiration = domainV.expiration
            )
        }
    }

    fun toDomain(): Verification {
        return Verification(
            contact =
            if (this.contact.contains("@"))
                Email.validEmail(this.contact)
            else
                PhoneNumber.validPhoneNumber(this.contact),
            verificationCode = VerificationCode(verificationNumber = this.verificationCode),
            expiration = this.expiration
        )
    }
}