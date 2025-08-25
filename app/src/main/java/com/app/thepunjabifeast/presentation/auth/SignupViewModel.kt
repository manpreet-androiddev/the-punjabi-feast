package com.app.thepunjabifeast.presentation.auth

import android.app.Application
import android.util.Log
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
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val validationHelper: ValidationHelper,
    networkStatusHelper: NetworkStatusHelper,
    private val applicationContext: Application
): BaseViewModel<ApiResult>(networkStatusHelper, applicationContext) {

    val userName = TextFieldState()  // 22 long , no number or special character
    val email = TextFieldState()
    val dob = TextFieldState()
    val phoneNumber = TextFieldState()
    val password = TextFieldState()
    val confirmPassword = TextFieldState()

    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
    }

    val isValidUsername by derivedStateOf {
        if (userName.text.toString().isNotEmpty()) {
            validationHelper.isValidUsername(userName.text.toString())
        } else false
    }

    val isValidEmail by derivedStateOf {
        if (email.text.toString().isNotEmpty()) {
            validationHelper.isValidEmail(email.text.toString())
        } else false
    }

    val isValidDob by derivedStateOf {
        if (email.text.toString().isNotEmpty()) {
            validationHelper.isValidEmail(email.text.toString())
        } else false
    }

    val isValidPhoneNumber by derivedStateOf {
        if (phoneNumber.text.toString().isNotEmpty()) {
            validationHelper.isValidPhoneNumber(phoneNumber.text.toString())
        } else false
    }

    val isValidPassword by derivedStateOf {
        if (password.text.toString().isNotEmpty()) {
            validationHelper.isValidPassword(password.text.toString())
        } else false
    }

    val isValidConfirmPassword by derivedStateOf {
        if (confirmPassword.text.toString().isNotEmpty()) {
            validationHelper.isValidConfirmPassword(password.text.toString(), confirmPassword.text.toString())
        } else false
    }

     fun isFormValid(): Boolean {
         Timber.d(isValidUsername.toString())

         val result = isValidUsername
                    && isValidEmail
                    && isValidPhoneNumber
                    && isValidPassword
                    && isValidConfirmPassword
         // && isValidDob
         return result
    }

    fun firebaseSignUp(showSnackBarError: (ErrorMessage) -> Unit) {
        viewModelScope.launch {
            executeRequest(showSnackBarError) {
                val response = repository.signUp(
                    email.text.toString(),
                    password.text.toString()
                )
                Log.d("firebase signup:", response.toString())
                ApiResult.FirebaseSignInResponse(response)
            }
        }
    }
}



