package com.app.thepunjabifeast.presentation.biometric

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.thepunjabifeast.presentation.biometric.BiometricPromptManager.*

@Composable
fun FingerBiometric(modifier: Modifier = Modifier, promptManager: BiometricPromptManager) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val biometricResult by promptManager.promptResults.collectAsState(
            initial = null
        )
        val enrollLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                println("Activity result: $it")
            }
        )
        LaunchedEffect(biometricResult) {
            if(biometricResult is BiometricResult.AuthenticationNotSet) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // 30 version code
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(
                            Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                        )
                    }
                    enrollLauncher.launch(enrollIntent)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    promptManager.showBiometricPromptPrompt(
                        title = "Sample prompt",
                        description = "sample prompt description"
                    )
                }) {
                Text(text = "Authenticate")
            }
            biometricResult.let { result ->
                Text(
                    text = when (result) {
                        is BiometricResult.AuthenticationError -> {
                            result.error
                        }

                        BiometricResult.AuthenticationFailed -> {
                            "Authentication Failed"
                        }

                        BiometricResult.AuthenticationNotSet -> {
                            "Authentication Not Set"
                        }

                        BiometricResult.AuthenticationSuccess -> {
                            "Authentication Success"
                        }

                        BiometricResult.FeatureUnavailable -> {
                            "Feature Unavailable"
                        }

                        BiometricResult.HardwareUnavailable -> {
                            "Hardware Unavailable"
                        }

                        null ->  "Feature Unavailable"
                    }
                )

            }
        }
    }
}