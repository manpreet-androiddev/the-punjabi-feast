package com.app.thepunjabifeast.data.model

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

/*typealias UserOrders = Map<Int, List<Order>>

fun processOrders(orders: UserOrders) {
    // Function implementation
}

typealias with function type

typealias Predicate<T> = (T) -> Boolean

typealias ClickListener = (View, Int) -> Unit

fun setOnClickListener(listener: ClickListener) {
    // Function implementation
}
*/

typealias SignOutResponse = AppUiState<Boolean>
typealias DeleteAccountResponse = AppUiState<Boolean>

sealed class AppUiState<out T> {
    data object Idle : AppUiState<Nothing>()
    data object Loading : AppUiState<Nothing>()
    data class Success<out T>(val data: T) : AppUiState<T>()
    data class Failure(val e: Throwable) : AppUiState<Nothing>()
}