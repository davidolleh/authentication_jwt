package com.example.authentication.authentication_jwt.domain.user

import com.example.authentication.authentication_jwt.domain.core.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user")
class User(
    email: Email?,
    phoneNumber: String?,
    name: String,
    imgUrl: String?,
): PrimaryKeyEntity() {

    @Embedded
    var email: Email? = email
        protected set

    @Column(nullable = true, unique = true)
    var phoneNumber: String? = phoneNumber
        protected set

    @Column(nullable = false, unique = true)
    var name: String = name
        protected set

    @Column(nullable = true)
    var imgUrl: String? = imgUrl

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()


}