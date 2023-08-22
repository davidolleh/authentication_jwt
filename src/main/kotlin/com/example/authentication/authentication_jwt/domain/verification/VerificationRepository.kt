package com.example.authentication.authentication_jwt.domain.verification

interface VerificationRepository {
    fun save(verification: Verification): Verification
    fun deleteByContact(contact: String): Boolean
    fun findById(): Verification
    fun findByContact(contact: String): Verification?
}