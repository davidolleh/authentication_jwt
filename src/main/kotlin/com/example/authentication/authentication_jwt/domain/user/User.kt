package com.example.authentication.authentication_jwt.domain.user

import com.example.authentication.authentication_jwt.domain.user.email.Email
import com.example.authentication.authentication_jwt.domain.user.phone_number.PhoneNumber
import jakarta.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    var userId: String,

    @Embedded
    var phoneNumber: PhoneNumber?,
    @Embedded
    var email: Email?,

    @Column(name="USER_PW")
    var password: String = "",

    @Column(name = "USER_NICKNM", unique = true)
    var nicknm: String = ""
) {
    fun satisfiedForSignUp(): Boolean = !(phoneNumber == null && email == null) && nicknm != null && password != null
}