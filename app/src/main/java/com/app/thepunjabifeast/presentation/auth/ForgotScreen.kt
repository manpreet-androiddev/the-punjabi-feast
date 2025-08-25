package com.app.thepunjabifeast.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.data.model.ErrorMessage
import com.app.thepunjabifeast.presentation.components.AppImage
import com.app.thepunjabifeast.ui.theme.ThePunjabiFeastTheme
import com.app.thepunjabifeast.presentation.components.AppButton
import com.app.thepunjabifeast.presentation.components.EditTextField
import com.app.thepunjabifeast.presentation.components.gradientColors
import kotlinx.serialization.Serializable

@Serializable
object ForgotRoute

@Composable
fun ForgotScreen(
    showErrorSnackBar: (ErrorMessage) -> Unit,
    forgotViewModel: ForgotViewModel = hiltViewModel(),
    modifier: Modifier,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientColors)
            .padding(horizontal = dimensionResource(R.dimen.padding_20)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppImage(
            screenHeight = screenHeight,
            modifier = Modifier
        )

        EditTextField(
            label = if (forgotViewModel.isValidEmail) R.string.wrong_email else R.string.email,
            leadingIcon = Icons.Filled.Email,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            onKeyBoardDone = {},
            isError = !forgotViewModel.isValidEmail,
            inputTransformation = InputTransformation,
            value = forgotViewModel.email,
            readOnly = false,
            outputTransformation = OutputTransformation {}
        )


        AppButton(
            R.string.send_link,
            onClick = {
                forgotViewModel.sendEmail(showErrorSnackBar)
            },
            isEnable = forgotViewModel.isValidEmail,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = dimensionResource(R.dimen.padding_small))
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForgotScreenPreview() {
    ThePunjabiFeastTheme {
       /* ForgotScreen(
            modifier = Modifier.fillMaxSize(),
        )*/
    }
}


