package com.app.thepunjabifeast.helpers

import androidx.compose.runtime.remember
import javax.inject.Inject



class ValidationHelperImpl @Inject constructor(): ValidationHelper {
    override fun isValidEmail(email: String): Boolean {
        val emailRegex = (
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
                ).toRegex()
        return emailRegex.matches(email)
    }

    override fun isValidPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}$")
        return regex.matches(password)
    }

    override fun isValidUsername(userName: String): Boolean {
        val pattern = Regex("[a-zA-Z]*")
        val userNameRegex = userName.matches(pattern) && userName.length in 6..22
        return userNameRegex
    }

    override fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val userNameRegex = phoneNumber.length == 9
        return userNameRegex
    }

    override fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
        val confirmPasswordState  = if (confirmPassword.isNotEmpty()) {
            password == confirmPassword
        } else false
        return confirmPasswordState
    }
}