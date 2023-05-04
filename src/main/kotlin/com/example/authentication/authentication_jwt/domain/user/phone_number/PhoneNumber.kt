package com.example.authentication.authentication_jwt.domain.user.phone_number

import com.example.authentication.authentication_jwt.domain.core.vo.Contact
import jakarta.persistence.Embeddable

@Embeddable
data class PhoneNumber(var phoneNumber: String) : Contact {
    init {
        if (!isInPhoneNumberFormat(phoneNumber=phoneNumber)) {
            throw PhoneNumberInvalidFormatException(phoneNumber = phoneNumber)
        }
    }

    override fun destination(): String = phoneNumber


    companion object {
        private val phoneNumberRegex = Regex("^[1-9]\\d{1,14}$")

        /**
         * 특정 string이 전화번호 형식에 맞는지 알려주는 함수
         * @param input 형식을 체크하고자 하는 string
         * @return [input]이 전화번호 형식에 맞는지에 대한 여부
         */
        fun isInPhoneNumberFormat(phoneNumber: String) : Boolean = phoneNumberRegex.matches(phoneNumber)
    }
}