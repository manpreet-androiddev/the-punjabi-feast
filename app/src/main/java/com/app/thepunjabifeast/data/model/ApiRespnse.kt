package com.app.thepunjabifeast.data.model

import com.google.firebase.auth.FirebaseUser

sealed class ApiResult {
    data class ServerLogin(val response: SignInResponse) : ApiResult()
    data class FirebaseSignInResponse(val response: FirebaseUser) : ApiResult()
    data class FirebaseSignOut(val response: Boolean) : ApiResult()
}
