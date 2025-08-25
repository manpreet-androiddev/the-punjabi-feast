package com.app.thepunjabifeast.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.OutlinedSecureTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalAutofillManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.ui.theme.Shapes
import com.app.thepunjabifeast.ui.theme.errorLight
import com.app.thepunjabifeast.ui.theme.primaryLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurePasswordEditField(
    @StringRes label: Int,
    state: TextFieldState,
    leadingIcon: ImageVector,
    inputTransformation: InputTransformation,
    keyboardOptions: KeyboardOptions,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

val autoFillManager = LocalAutofillManager.current

    OutlinedSecureTextField(
        state = state,
        shape = Shapes.small,
        label = {
            Text(
                text = stringResource(label),
                color = Color.Black,
                fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.text_default).toSp() },
            )
        },
        inputTransformation = inputTransformation,
        isError = isError,
        textObfuscationMode =
            if (passwordHidden) TextObfuscationMode.RevealLastTyped
            else TextObfuscationMode.Visible,
        trailingIcon = {
            if(state.text.toString().isNotEmpty()) {
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = primaryLight,
                        disabledContentColor = Color.LightGray
                    ),
                    onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    // Provide localized description for accessibility services
                    val description = if (passwordHidden) stringResource(R.string.show_password) else stringResource(R.string.hide_password)
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            } else {
                val tooltipState = rememberTooltipState()
                val scope = rememberCoroutineScope()
                TooltipBox(
                    modifier = modifier,
                    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                    tooltip = {
                        PlainTooltip { Text(stringResource(R.string.password_info)) }
                    },
                    state = tooltipState
                ) {
                    IconButton(onClick = { scope.launch { tooltipState.show() } }) {
                        Icon(
                            imageVector = Icons.Rounded.Info,
                            tint = primaryLight,
                            contentDescription = stringResource(R.string.password_info)
                        )
                    }
                }
            }
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = primaryLight,
                modifier = Modifier.size(dimensionResource(R.dimen.icon_default))
            )
        },
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primaryLight,
            unfocusedBorderColor = primaryLight,
            disabledBorderColor = Color.LightGray,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            errorBorderColor = errorLight,
            disabledTextColor = Color.Gray,
            leadingIconColor = primaryLight,
            trailingIconColor = primaryLight,
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.cabin_regular)),
            fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.text_default).toSp() },
        ),
        modifier = Modifier
            .semantics { contentType = ContentType.Password }
            .fillMaxWidth()
    )
}