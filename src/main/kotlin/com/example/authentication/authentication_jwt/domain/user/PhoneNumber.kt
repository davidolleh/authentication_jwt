package com.example.authentication.authentication_jwt.domain.user

import com.example.authentication.authentication_jwt.config.exception.InvalidValueException
import jakarta.persistence.Embeddable


@Embeddable
@NoArg
data class PhoneNumber private constructor(
    var phoneNumber: String
): Contact {
    companion object {
//        private const val phoneNumberPattern: String = "^[1-9]\\d{1,14}$"
        private const val phoneNumberPattern: String = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$"

        fun validPhoneNumber(userInput: String) : PhoneNumber =
            if (Regex(phoneNumberPattern).matches(userInput)) PhoneNumber(userInput)
            else throw InvalidValueException()
    }

    override fun readDestination(): String = this.phoneNumber
}
