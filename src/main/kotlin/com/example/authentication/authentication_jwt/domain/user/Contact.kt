package com.example.authentication.authentication_jwt.domain.user

import jakarta.persistence.Embeddable

sealed class Contact(
    val information: String
)