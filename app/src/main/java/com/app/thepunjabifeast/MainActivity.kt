package com.app.thepunjabifeast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.thepunjabifeast.ui.theme.ThePunjabiFeastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThePunjabiFeastTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
   /* Log.i(TAG, BuildConfig.BUILD_TIME)
    Log.i(TAG, getString(R.string.build_time))

    storePassword = System.getenv("KSTOREPWD")
    keyPassword = System.getenv("KEYPWD")

    val contentUri: Uri = FileProvider.getUriForFile(context, BuildConfig.FILES_AUTHORITY, myFile)*/
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThePunjabiFeastTheme {
        Greeting("Android")
    }
}