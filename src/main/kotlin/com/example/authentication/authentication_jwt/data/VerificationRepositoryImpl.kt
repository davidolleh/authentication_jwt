package com.example.authentication.authentication_jwt.data

import com.example.authentication.authentication_jwt.domain.verification.Verification
import com.example.authentication.authentication_jwt.domain.verification.VerificationRepository
import org.springframework.stereotype.Repository


@Repository
class VerificationRepositoryImpl: VerificationRepository {
    override fun save(verification: Verification): Verification {
        return verification
    }

    override fun delete(): Verification {
        TODO("Not yet implemented")
    }

    override fun findById(): Verification {
        TODO("Not yet implemented")
    }
}