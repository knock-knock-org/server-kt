package com.knockknock.server.utils


// phone number format
const val PHONE_NO_FORMAT = "^\\d{3}-\\d{4}-\\d{4}$"

fun validatePhoneNoFormat (phoneNo: String): Boolean = phoneNo.matches(PHONE_NO_FORMAT.toRegex())

fun convertPhoneNoWithoutDash (phoneNo: String): String {
    val convertedPhoneNo: String = phoneNo.replace("-","")

    return convertedPhoneNo;
}