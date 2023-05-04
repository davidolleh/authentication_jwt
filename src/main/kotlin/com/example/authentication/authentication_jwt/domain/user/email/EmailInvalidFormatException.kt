package com.example.authentication.authentication_jwt.domain.user.email

import com.example.authentication.authentication_jwt.domain.core.exception.InvalidFormatException

class EmailInvalidFormatException(email: String) : InvalidFormatException(dataType=email)