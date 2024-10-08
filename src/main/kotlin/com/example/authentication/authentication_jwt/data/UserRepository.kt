package com.example.authentication.authentication_jwt.data

import com.example.authentication.authentication_jwt.domain.user.Email
import com.example.authentication.authentication_jwt.domain.user.PhoneNumber
import com.example.authentication.authentication_jwt.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
    fun findByEmail(email: Email): User

    fun findByPhoneNumber(phoneNumber: PhoneNumber): User
}