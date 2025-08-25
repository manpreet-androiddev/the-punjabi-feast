package com.app.thepunjabifeast.presentation.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.data.MainPagerDataSource
import com.app.thepunjabifeast.data.model.MainPager
import com.app.thepunjabifeast.ui.theme.ThePunjabiFeastTheme
import com.app.thepunjabifeast.ui.theme.primaryContainerLight
import com.app.thepunjabifeast.ui.theme.primaryLight
import com.app.thepunjabifeast.ui.theme.surfaceContainerLight
import com.app.thepunjabifeast.presentation.components.FlippedWaveFooter
import kotlinx.serialization.Serializable
import kotlin.math.absoluteValue

@Serializable
object AppIntroRoute

@Composable
fun PFMainPager(
    openAuthScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 8 })

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->

            MainPagerItem(
                mainPagerData = MainPagerDataSource.mainPagerData[page],
                page = page,
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.3f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            )
        }

        PagerIndicator(
            currentPage = pagerState.currentPage,
            pageCount = MainPagerDataSource.mainPagerData.size,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = dimensionResource(R.dimen.padding_default))
        )

        IconButton(
            onClick = openAuthScreen,
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(R.string.close),
                tint = primaryLight,
                modifier = modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .background(primaryContainerLight, RoundedCornerShape(5.dp))
                    .size(dimensionResource(R.dimen.icon_default))
            )
        }
    }
}

@Composable
fun MainPagerItem(
    mainPagerData: MainPager,
    page: Int,
    modifier: Modifier = Modifier,
) {

    Box(modifier = modifier) {
        Image(
            painter = painterResource(mainPagerData.pagerImgRes),
            contentDescription = stringResource(R.string.main_pager_img_desc, page + 1),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-200).dp)
        )

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .clip(FlippedWaveFooter)
                .background(surfaceContainerLight)
                .align(Alignment.BottomCenter)
                .padding(bottom = dimensionResource(R.dimen.padding_extra_large))
                .fillMaxWidth()
                .height(450.dp)
        ) {

            Text(
                text = stringResource(mainPagerData.pagerStringRes),
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily(Font(R.font.satisfy)),
                    fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.text_large).toSp() },
                ),
                modifier = Modifier
                    .offset(y = (-60).dp)
            )
        }
    }
}

@Composable
fun PagerIndicator(currentPage: Int, pageCount: Int, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        repeat(pageCount) { index ->
            val color = if (index == currentPage) primaryLight else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PFMainPagerPreview(modifier: Modifier = Modifier) {
    ThePunjabiFeastTheme {
        PFMainPager(
            openAuthScreen = {}
        )
    }
}

@Preview
@Composable
fun PagerPreview(modifier: Modifier = Modifier) {
    MainPagerItem(MainPager(R.string.main_pager6, R.drawable.main_pager6), 6)
}
