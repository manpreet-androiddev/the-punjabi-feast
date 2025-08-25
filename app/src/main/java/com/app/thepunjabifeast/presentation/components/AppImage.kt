package com.app.thepunjabifeast.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.ui.theme.Shapes

@Composable
fun AppImage(
    screenHeight: Dp,
    modifier: Modifier = Modifier
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