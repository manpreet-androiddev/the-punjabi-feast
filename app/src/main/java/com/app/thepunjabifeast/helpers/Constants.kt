package com.app.thepunjabifeast.helpers

const val ERR_LEN = "Password must have at least eight characters!"
const val ERR_WHITESPACE = "Password must not contain whitespace!"
const val ERR_DIGIT = "Password must contain at least one digit!"
const val ERR_UPPER = "Password must have at least one uppercase letter!"
const val ERR_SPECIAL = "Password must have at least one special character, such as: _%-=+#@"
const val SIGN_IN_REQUEST = "signInRequest"
const val SIGN_UP_REQUEST = "signUpRequest"

object AuthErrors {
    const val CREDENTIAL_ALREADY_IN_USE = "ERROR_CREDENTIAL_ALREADY_IN_USE"
    const val EMAIL_ALREADY_IN_USE = "ERROR_EMAIL_ALREADY_IN_USE"
}