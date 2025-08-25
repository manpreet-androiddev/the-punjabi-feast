package com.app.thepunjabifeast.presentation.auth

import android.app.Activity
import android.app.Application
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import com.app.thepunjabifeast.helpers.ValidationHelper
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.data.repository.AuthRepository
import com.app.thepunjabifeast.helpers.NetworkStatusHelper
import com.app.thepunjabifeast.data.model.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import android.util.Log
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import com.app.thepunjabifeast.data.model.ApiResult
import com.app.thepunjabifeast.helpers.BaseViewModel

@HiltViewModel
 class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val validationHelper: ValidationHelper,
    private val request: GetCredentialRequest,
    private val credentialManager: CredentialManager,
    networkStatusHelper: NetworkStatusHelper,
    private val applicationContext: Application
) : BaseViewModel<ApiResult>(networkStatusHelper, applicationContext) {

    val passwordState = TextFieldState()
    val emailState = TextFieldState()

    val isValidEmail by derivedStateOf {
        if (emailState.text.toString().isNotEmpty()) {
            validationHelper.isValidEmail(emailState.text.toString())
        } else false
    }

    val isValidPassword by derivedStateOf {
        if (passwordState.text.toString().isNotEmpty()) {
            validationHelper.isValidPassword(passwordState.text.toString())
        } else false
    }

    fun isFormValid(): Boolean {
        val result = isValidPassword && isValidEmail
        return result
    }

    fun firebaseSignIn(showSnackBarError: (ErrorMessage) -> Unit) {
        viewModelScope.launch {
            executeRequest(showSnackBarError) {
                val response = repository.signIn(
                    emailState.text.toString(),
                    passwordState.text.toString()
                )
                Log.d("firebase Login:", response.toString())
                ApiResult.FirebaseSignInResponse(response)
            }
        }
    }
    fun loginRequest(showSnackBarError: (ErrorMessage) -> Unit) {
        viewModelScope.launch {
            executeRequest(showSnackBarError) {
                val response = repository.login(
                    emailState.text.toString(),
                    passwordState.text.toString()
                )
                ApiResult.ServerLogin(response)
            }
        }
    }

    fun signInWithGoogle(showSnackBarError: (ErrorMessage) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = credentialManager.getCredential(
                    context = applicationContext,
                    request = request
                )

                if (result.credential is CustomCredential &&
                    result.credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                ) {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(result.credential.data)

                    executeRequest(showSnackBarError) {
                        val response = repository.signInWithGoogle(googleIdTokenCredential.idToken)
                        ApiResult.FirebaseSignInResponse(response)
                    }
                }
            } catch (e: GetCredentialCancellationException) {
                showSnackBarError(ErrorMessage.IdError(R.string.signIn_cancelled))
            } catch (e: NoCredentialException) {
                showSnackBarError(ErrorMessage.IdError(R.string.no_credentials_found))
            } catch (e: Exception) {
                showSnackBarError(ErrorMessage.StringError("Sign-in failed: ${e.message}"))
            }
        }
    }

    fun signInWithTwitter(activity: Activity, showSnackBarError: (ErrorMessage) -> Unit) {
        viewModelScope.launch {
            executeRequest(showSnackBarError) {
                val response = repository.signInWithTwitter(activity)
                ApiResult.FirebaseSignInResponse(response)
            }
        }
    }

    fun signOut(showSnackBarError: (ErrorMessage) -> Unit) {
        viewModelScope.launch {
            executeRequest(showSnackBarError) {
                val response = repository.signOut()
                ApiResult.FirebaseSignOut(response)
            }
        }
    }

    fun signInAnonymously(showSnackBarError: (ErrorMessage) -> Unit) {
        viewModelScope.launch {
            executeRequest(showSnackBarError) {
                val response = repository.signInAnonymously()
                ApiResult.FirebaseSignInResponse(response)
            }
        }
    }
}