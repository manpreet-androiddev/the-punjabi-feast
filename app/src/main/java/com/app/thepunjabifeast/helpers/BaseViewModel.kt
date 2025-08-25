package com.app.thepunjabifeast.helpers

import android.app.Application
import androidx.credentials.exceptions.ClearCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.ViewModel
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.data.model.AppUiState
import com.app.thepunjabifeast.data.model.ErrorMessage
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

open class BaseViewModel<T>(
    networkStatusHelper: NetworkStatusHelper,
    private val applicationContext: Application
) : ViewModel() {

    private val isConnected: StateFlow<Boolean> = networkStatusHelper.connection
    private val _uiState = MutableStateFlow<AppUiState<T>>(AppUiState.Idle)
    val uiState = _uiState.asStateFlow()

    suspend fun executeRequest(
        showSnackBarError: (ErrorMessage) -> Unit,
        request: suspend () -> T
    ) {
        if (!isConnected.value) {
            showSnackBarError(ErrorMessage.IdError(R.string.internet_not_connected))
            return
        }

        _uiState.value = AppUiState.Loading
        try {
            val result = request()
            _uiState.value = AppUiState.Success(result)
            Timber.i("Request success: %s", result)

        } catch (e: NoCredentialException) {
            Timber.w(e, "No credentials found during request")
            showSnackBarError(ErrorMessage.IdError(R.string.no_credentials_found))
        } catch (e: ClearCredentialException) {
            showSnackBarError(
                ErrorMessage.StringError(
                    applicationContext.getString(R.string.credentials_not_cleared, e.localizedMessage)
                )
            )
        } catch (e: Throwable) {
            Timber.e(e, "Request failed")
            Firebase.crashlytics.recordException(e)
            _uiState.value = AppUiState.Failure(e)

            val error = if (e.message.isNullOrBlank()) {
                ErrorMessage.IdError(R.string.generic_error)
            } else {
                ErrorMessage.StringError(e.message!!)
            }
            showSnackBarError(error)
        }
    }
}