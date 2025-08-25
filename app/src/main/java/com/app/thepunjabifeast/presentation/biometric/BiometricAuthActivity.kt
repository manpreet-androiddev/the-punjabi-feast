package com.app.thepunjabifeast.presentation.biometric

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.app.thepunjabifeast.ui.theme.ThePunjabiFeastTheme

class BiometricAuthActivity : AppCompatActivity() {

    private val promptManager by lazy {
        BiometricPromptManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThePunjabiFeastTheme {
                Scaffold (
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    FingerBiometric(
                        promptManager = promptManager,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}