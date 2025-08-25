package com.app.thepunjabifeast

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import android.view.WindowManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.app.thepunjabifeast.presentation.auth.LoginSignup
import com.app.thepunjabifeast.presentation.MainViewModel
import com.app.thepunjabifeast.data.model.ErrorMessage
import com.app.thepunjabifeast.presentation.intro.AppIntroRoute
import com.app.thepunjabifeast.ui.theme.ThePunjabiFeastTheme
import androidx.navigation.compose.composable
import com.app.thepunjabifeast.presentation.auth.AuthRoute
import com.app.thepunjabifeast.presentation.auth.ForgotRoute
import com.app.thepunjabifeast.presentation.auth.ForgotScreen
import com.app.thepunjabifeast.presentation.auth.ForgotViewModel
import com.app.thepunjabifeast.presentation.auth.LoginViewModel
import com.app.thepunjabifeast.presentation.auth.SignupViewModel
import com.app.thepunjabifeast.utilites.TestRoute
import com.app.thepunjabifeast.utilites.TestScreen
import com.app.thepunjabifeast.presentation.intro.PFMainPager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// IwzAf2v5oZwJ7rK8l1aaGayO4 -> twitter key
// 7YgakNYpGjzJJJBoRl9lFMzhOe6h8E4QT2UPzfd3Kj615m7UgT -> twitter secret
// https://punjabifeast-b10a5.firebaseapp.com/__/auth/handler -> callback url

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val loginViewModel by viewModels<LoginViewModel>()
    private val sinUpViewModel by viewModels<SignupViewModel>()
    private val forgotViewModel by viewModels<ForgotViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }

            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )

                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd { screen.remove() }
                zoomX.start()
                zoomY.start()
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setSoftInputMode()

        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val scope = rememberCoroutineScope()
            val snackBarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()

            ThePunjabiFeastTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = AppIntroRoute,
                            modifier = Modifier.padding(innerPadding)
                        ) {

                            composable<AppIntroRoute> { PFMainPager(
                                openAuthScreen = {
                                    navController.popBackStack()
                                    navController.navigate(AuthRoute) { launchSingleTop = true }
                                }
                            ) }

                            composable<AuthRoute> {
                                LoginSignup(
                                    openHomeScreen = {
                                      //  navController.navigate() { launchSingleTop = true }
                                    },
                                    openForgotScreen = {
                                        navController.navigate(ForgotRoute) { launchSingleTop = true }
                                    },
                                    showErrorSnackBar = { errorMessage ->
                                        val message = getErrorMessage(errorMessage)
                                        scope.launch { snackBarHostState.showSnackbar(message) }
                                    } ,
                                    activity = this@MainActivity,
                                    loginViewModel = loginViewModel,
                                    sinUpViewModel = sinUpViewModel,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }

                            composable<ForgotRoute> {
                                ForgotScreen(
                                    showErrorSnackBar = { errorMessage ->
                                        val message = getErrorMessage(errorMessage)
                                        scope.launch { snackBarHostState.showSnackbar(message) }
                                    } ,
                                    forgotViewModel = forgotViewModel,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }

                            composable<TestRoute> {
                                TestScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setSoftInputMode() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }

    private fun getErrorMessage(error: ErrorMessage): String {
        return when (error) {
            is ErrorMessage.StringError -> error.message
            is ErrorMessage.IdError -> this@MainActivity.getString(error.message)
        }
    }
}



/*
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Log.i(TAG, BuildConfig.BUILD_TIME)
     Log.i(TAG, getString(R.string.build_time))

     storePassword = System.getenv("KSTOREPWD")
     keyPassword = System.getenv("KEYPWD")

     val contentUri: Uri = FileProvider.getUriForFile(context, BuildConfig.FILES_AUTHORITY, myFile)
}
*/
