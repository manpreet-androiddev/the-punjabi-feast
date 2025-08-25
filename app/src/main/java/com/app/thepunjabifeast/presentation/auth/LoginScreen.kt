package com.app.thepunjabifeast.presentation.auth

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.data.model.ErrorMessage
import com.app.thepunjabifeast.ui.theme.ThePunjabiFeastTheme
import com.app.thepunjabifeast.ui.theme.primaryContainerLight
import com.app.thepunjabifeast.ui.theme.primaryLight
import com.app.thepunjabifeast.presentation.components.AppButton
import com.app.thepunjabifeast.presentation.components.EditTextField
import com.app.thepunjabifeast.presentation.components.SecurePasswordEditField

@Composable
fun LoginScreen(
    openHomeScreen: () -> Unit,
    openForgotScreen: () -> Unit,
    showErrorSnackBar: (ErrorMessage) -> Unit,
    activity: Activity? = null,
    modifier: Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_20)),
    ) {
        EditTextField(
            label = if (loginViewModel.isValidEmail) R.string.wrong_email else R.string.email,
            leadingIcon = Icons.Filled.Email,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            onKeyBoardDone = {},
            isError = !loginViewModel.isValidEmail,
            inputTransformation = InputTransformation,
            value = loginViewModel.emailState,
            readOnly = false,
            outputTransformation = OutputTransformation {}
        )

        SecurePasswordEditField(
            label = R.string.password,
            state = loginViewModel.passwordState,
            leadingIcon = Icons.Filled.Lock,
            isError = !loginViewModel.isValidPassword,
            inputTransformation = InputTransformation.maxLength(15),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
        )

        RememberAndForgotPassword(openForgotScreen)

        AppButton(
            R.string.login,
            onClick = {
                loginViewModel.firebaseSignIn(showErrorSnackBar)
            },
            isEnable = loginViewModel.isFormValid(),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = dimensionResource(R.dimen.padding_small))
        )

        SocialLogin(
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(R.dimen.padding_default),
                    horizontal = dimensionResource(R.dimen.padding_large)
                ),
            onGoogleClick = { loginViewModel.signInWithGoogle(showErrorSnackBar) },
            signInWithTwitter = {
                activity?.let {
                    loginViewModel.signInWithTwitter(
                        it,
                        showErrorSnackBar
                    )
                }
            },
            onSignOut = { loginViewModel.signOut(showErrorSnackBar) }
        )
    }
}

@Composable
fun RememberAndForgotPassword(openForgotScreen: () -> Unit) {

    var checked by remember { mutableStateOf(true) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_medium))
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = primaryLight,
                    uncheckedColor = primaryContainerLight,
                ),
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = dimensionResource(R.dimen.padding_medium))
            )
            Text(
                text = stringResource(R.string.remember_me),
            )
        }

        Text(
            text = stringResource(R.string.forgot_password),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.cabin_regular)),
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.text_default).toSp() },
            ),
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(
                        bounded = true,
                        color = Color.Black.copy(alpha = 0.7f)
                    )
                ) {
                    openForgotScreen()
                }
        )
    }
}

@Composable
fun SocialLogin(
    modifier: Modifier,
    onGoogleClick: () -> Unit,
    signInWithTwitter: () -> Unit,
    onSignOut: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(R.string.continue_with),
            fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.text_default).toSp() },
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(8.dp))

        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .wrapContentHeight()
            .padding(horizontal = dimensionResource(R.dimen.padding_default))

    ) {

        IconButton(
            onClick = onGoogleClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_gmail),
                contentDescription = stringResource(R.string.login_with_gmail),
                tint = primaryLight,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_default))
            )
        }

        IconButton(
            onClick = signInWithTwitter
        ) {
            Image(
                painter = painterResource(R.drawable.ic_twitter),
                contentDescription = stringResource(R.string.login_with_twitter),
                colorFilter = ColorFilter.tint(primaryLight),
                modifier = Modifier.size(dimensionResource(R.dimen.icon_default))
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    ThePunjabiFeastTheme {
        val fakeActivity = Activity()
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            openHomeScreen = {},
            openForgotScreen = {},
            showErrorSnackBar = {},
            activity = fakeActivity,
        )
    }
}


