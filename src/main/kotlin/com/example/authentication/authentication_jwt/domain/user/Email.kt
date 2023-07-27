package com.example.authentication.authentication_jwt.domain.user

import com.example.authentication.authentication_jwt.domain.core.Contact
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Email private constructor(
    var email: String
): Contact(destination = email) {
    companion object {
        private const val localPattern = "([a-z0-9!#\\\$%&'*+/=?^_`{|}~-]+\\.?[a-z0-9!#\\\$%&'*+/=?^_`{|}~-]+)"

        private const val domainPattern = "[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+"

        private const val emailPattern = "^${localPattern}@${domainPattern}$"

        fun validEmail(userInput: String): Email =
            if(userInput.length <= 200 && Regex(emailPattern).matches(userInput)) Email(userInput) else throw Exception()
    }
}