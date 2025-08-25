package com.app.thepunjabifeast.presentation.auth

import android.app.Application
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.app.thepunjabifeast.data.repository.AuthRepository
import com.app.thepunjabifeast.helpers.NetworkStatusHelper
import com.app.thepunjabifeast.helpers.ValidationHelper
import com.app.thepunjabifeast.data.model.ApiResult
import com.app.thepunjabifeast.data.model.ErrorMessage
import com.app.thepunjabifeast.helpers.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val validationHelper: ValidationHelper,
    networkStatusHelper: NetworkStatusHelper,
    private val applicationContext: Application
): BaseViewModel<ApiResult>(networkStatusHelper, applicationContext) {

    val email = TextFieldState()

    val isValidEmail by derivedStateOf {
        if (email.text.toString().isNotEmpty()) {
            validationHelper.isValidEmail(email.text.toString())
        } else false
    }

    fun sendEmail(showSnackBarError: (ErrorMessage) -> Unit) {
        viewModelScope.launch {
          /*  executeRequest(showSnackBarError) {
                val response = repository.signUp(
                    email.text.toString()
                )
                Log.d("firebase signup:", response.toString())
                ApiResult.FirebaseSignInResponse(response)
            }*/
        }
    }
}

