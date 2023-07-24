package com.example.authentication.authentication_jwt.domain.user

import com.example.authentication.authentication_jwt.domain.core.PrimaryKeyEntity
import jakarta.persistence.AttributeOverride
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
    @AttributeOverride(
        name="email", column = Column(name="USER_EMAIL", nullable = true, unique = true)
    )
    var email: Email? = email
        protected set

    @Embedded
    @AttributeOverride(
        name="phoneNumber", column = Column(name="USER_PHONE_NUMBER", nullable = true, unique = true)
    )
    var phoneNumber: String? = phoneNumber
        protected set

    @Column(name = "USER_NAME", nullable = false, unique = true)
    var name: String = name
        protected set

    @Column(name="USER_IMAGE_URL", nullable = true)
    var imgUrl: String? = imgUrl

    @Column(name="USER_CREATE_TIME", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
}