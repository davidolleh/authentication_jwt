package com.example.authentication.authentication_jwt.domain.user

import jakarta.persistence.Embeddable


@Embeddable
@NoArg
data class PhoneNumber private constructor(
    var phoneNumber: String
): Contact {
    companion object {
        private const val phoneNumberPattern: String = "^[1-9]\\d{1,14}$"

        fun validPhoneNumber(userInput: String) : PhoneNumber =
            if (Regex(phoneNumberPattern).matches(userInput)) PhoneNumber(userInput) else throw Exception()
    }

    override fun destination(): String = this.phoneNumber
}
