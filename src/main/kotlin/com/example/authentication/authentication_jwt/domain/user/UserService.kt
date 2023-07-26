package com.example.authentication.authentication_jwt.domain.user

import com.example.authentication.authentication_jwt.data.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) {
    private val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Transactional
    fun singUp(email: String?, phoneNumber: String?, pw: String) : Unit {
        val encryptedPassword: String = passwordEncoder.encode(pw)
    }

    private fun checkIdIsExist(email: String?, phoneNumber: String?): Boolean {
        var user: User? = null
        if (email != null) {
            user = userRepository.findByEmail(Email.validEmail(email))
        } else {
            user = userRepository.findByPhoneNumber(PhoneNumber.validPhoneNumber(phoneNumber!!))
        }

        return user != null
    }


    @Transactional
    fun login(): Unit {

    }

    @Transactional
    fun findPassword(): Unit {

    }

    @Transactional
    fun resetPassword() : Unit {}

    @Transactional
    fun signOut(): Unit {}
}