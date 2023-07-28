//package com.example.authentication.authentication_jwt.domain.sms
//
//import com.example.authentication.authentication_jwt.domain.user.Contact
//import com.example.authentication.authentication_jwt.domain.user.Email
//import com.example.authentication.authentication_jwt.domain.user.PhoneNumber
//import org.springframework.beans.factory.annotation.Autowired
//
//class SmsService @Autowired constructor() {
//    fun sendVerificationToDestination(contact: Contact) {
//        when (contact) {
//            is PhoneNumber -> sendVerificationToSms(
//                phoneNumber = PhoneNumber.validPhoneNumber(contact.destination),
//                countryCode = "82"
//            )
//            is PhoneNumber -> sendVerificationToEmail(
//                email=Email.validEmail(contact.destination)
//            )
//        }
//    }
//
//    private fun sendVerificationToSms(phoneNumber: PhoneNumber, countryCode: String) {
//    }
//
//    private fun sendVerificationToEmail(email: Email) {
//
//    }
//}