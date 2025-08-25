package com.app.thepunjabifeast.helpers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStatusHelper @Inject constructor(
    connectivityObserver: ConnectivityObserver
) {
    val connection: StateFlow<Boolean> = connectivityObserver.isConnected
        .stateIn(
            CoroutineScope(Dispatchers.IO + SupervisorJob()),
            SharingStarted.Eagerly,
            false
        )
}