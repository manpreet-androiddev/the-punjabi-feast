package com.app.thepunjabifeast.utilites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.thepunjabifeast.presentation.components.gradientColors
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.ui.theme.Shapes
import kotlinx.serialization.Serializable

@Serializable
object TestRoute

@Composable
fun TestScreen(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .background(brush = gradientColors),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


            Image(
                painter = painterResource(R.drawable.app_bg_img),
                contentDescription = stringResource(R.string.bg_desc),
                modifier = Modifier
                    .height(screenHeight * 0.25f)
                    .alpha(0.5f)
                    .clip(Shapes.large),
                contentScale = ContentScale.Crop
            )

    }
}




