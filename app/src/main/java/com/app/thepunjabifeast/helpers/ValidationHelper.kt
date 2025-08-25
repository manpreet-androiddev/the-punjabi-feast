package com.app.thepunjabifeast.helpers

interface ValidationHelper {
    fun isValidEmail(email: String): Boolean
    fun isValidPassword(password: String): Boolean
    fun isValidUsername(userName: String): Boolean
    fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean
    fun isValidPhoneNumber(phoneNumber: String): Boolean
}