package com.example.authentication.authentication_jwt.domain.user.phone_number

import com.example.authentication.authentication_jwt.domain.core.exception.InvalidFormatException

class PhoneNumberInvalidFormatException(phoneNumber: String): InvalidFormatException(dataType =phoneNumber)