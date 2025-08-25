package com.app.thepunjabifeast.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.ui.theme.Shapes
import com.app.thepunjabifeast.ui.theme.tertiaryContainerDarkMediumContrast


@Composable
fun AppButton(
    @StringRes label: Int,
    onClick: () -> Unit,
    isEnable: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = isEnable,
        colors = ButtonDefaults.buttonColors(
            containerColor = tertiaryContainerDarkMediumContrast,     // 🔷 Background color
            contentColor = Color.White,   // 🔷 Text/icon color
            disabledContainerColor = Color.Gray,
        ),
        shape = Shapes.extraSmall,
        modifier = modifier

    ) {
        Text(
            text = stringResource(label),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.cabin_regular)),
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.text_heading).toSp() }
            )
        )
    }
}