package com.app.thepunjabifeast.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse (
    @SerialName("kind"         ) var kind         : String,
    @SerialName("idToken"      ) var idToken      : String,
    @SerialName("email"        ) var email        : String,
    @SerialName("refreshToken" ) var refreshToken : String,
    @SerialName("expiresIn"    ) var expiresIn    : String,
    @SerialName("localId"      ) var localId      : String

)