package com.app.thepunjabifeast.data.model

import androidx.compose.foundation.text.input.TextFieldState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
)

data class SignInResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
)
