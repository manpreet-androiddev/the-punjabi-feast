package com.app.thepunjabifeast.presentation.auth

import android.app.Activity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.thepunjabifeast.presentation.components.gradientColors
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.data.model.AppUiState
import com.app.thepunjabifeast.data.model.ErrorMessage
import com.app.thepunjabifeast.presentation.components.AppImage
import com.app.thepunjabifeast.ui.theme.primaryLight
import com.app.thepunjabifeast.presentation.components.LoadingScreen
import com.app.thepunjabifeast.presentation.components.PFPagerIndicator
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
object AuthRoute

@Composable
fun LoginSignup(
    openHomeScreen: () -> Unit,
    openForgotScreen: () -> Unit,
    showErrorSnackBar: (ErrorMessage) -> Unit,
    activity: Activity,
    loginViewModel: LoginViewModel,
    sinUpViewModel: SignupViewModel,
    modifier: Modifier = Modifier
) {

    val pagerState = rememberPagerState(pageCount = { 2 })
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val currentUiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientColors),
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                AppImage(
                    screenHeight = screenHeight,
                    modifier = Modifier
                )
            }

            item { Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_default))) }

            item {
                NavigationTab(
                    pagerState = pagerState,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(45.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.White)
                )
            }

            item { Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_default))) }

            item {
                HorizontalPager(
                    state = pagerState,
                    userScrollEnabled = false,
                    modifier = Modifier
                        .fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> {

                            LoginScreen(
                                openHomeScreen = openHomeScreen,
                                openForgotScreen = openForgotScreen,
                                showErrorSnackBar = showErrorSnackBar,
                                activity = activity,
                                modifier = Modifier,
                                loginViewModel = loginViewModel
                            )
                        }

                        1 -> {
                            SignupScreen(
                                modifier = Modifier,
                                showErrorSnackBar = showErrorSnackBar,
                                signupViewModel = sinUpViewModel,
                            )
                        }
                    }
                }
            }
        }
    }

    when (currentUiState) {
        is AppUiState.Idle -> {}

        is AppUiState.Loading -> {
            LoadingScreen()
        }

        is AppUiState.Success -> {
            val data = (currentUiState as AppUiState.Success).data
            Text("Login: $data")
        }

        is AppUiState.Failure -> {
            val error = (currentUiState as AppUiState.Failure).e.message
          //  showErrorSnackBar(ErrorMessage.StringError(error!!))
        }
    }
}


@Composable
fun NavigationTab(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var tabState by remember { mutableIntStateOf(0) }
    val tabTitles = listOf(R.string.login, R.string.sign_up)
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        PFPagerIndicator(tabPositions, tabState)
    }
    Row(
        modifier = modifier,
    ) {
        TabRow(
            selectedTabIndex = tabState,
            modifier = Modifier
                .fillMaxSize(),
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            indicator = indicator,
            divider = {}
        ) {
            tabTitles.forEachIndexed { index, title ->
                val isSelected = tabState == index
                val contentColor by animateColorAsState(
                    if (isSelected) Color.White else primaryLight,
                    label = "tab_content"
                )
                Tab(
                    selected = isSelected,
                    onClick = {
                        tabState = index
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .zIndex(6f)
                        .clip(RoundedCornerShape(30.dp)),
                    text = {
                        if (isSelected) {
                            Box(
                                modifier = Modifier
                                    .padding(dimensionResource(R.dimen.padding_medium))
                            ) {
                                Text(text = stringResource(title))
                            }
                        } else {
                            Text(text = stringResource(title))
                        }
                    },
                    selectedContentColor = contentColor,
                    unselectedContentColor = contentColor,
                )
            }
        }
    }
}




