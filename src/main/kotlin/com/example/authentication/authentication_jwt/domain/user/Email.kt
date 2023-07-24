package com.example.authentication.authentication_jwt.domain.user

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Email private constructor(
    @Column(name = "email", nullable = true, unique = true)
    var email: String
) {
    companion object {
        private const val localPattern = "([a-z0-9!#\\\$%&'*+/=?^_`{|}~-]+\\.?[a-z0-9!#\\\$%&'*+/=?^_`{|}~-]+)"

        private const val domainPattern = "[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+"

        private const val emailPattern = "^${localPattern}@${domainPattern}$"

        fun validEmail(userInput: String): Email =
            if(userInput.length <= 200 && Regex(emailPattern).matches(userInput)) Email(userInput) else throw Exception()
    }
}