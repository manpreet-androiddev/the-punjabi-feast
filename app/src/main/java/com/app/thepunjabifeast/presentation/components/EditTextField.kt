package com.app.thepunjabifeast.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.ui.theme.Shapes
import com.app.thepunjabifeast.ui.theme.primaryLight
import com.app.thepunjabifeast.ui.theme.errorLight
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.onFocusChanged
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EditTextField(
    label: Int,
    leadingIcon: ImageVector,
    keyboardOptions: KeyboardOptions,
    value: TextFieldState,
    isError: Boolean,
    inputTransformation: InputTransformation,
    outputTransformation: OutputTransformation,
    onKeyBoardDone: () -> Unit,
    readOnly: Boolean,
    modifier: Modifier = Modifier
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    OutlinedTextField(
        state = value,
        shape = Shapes.small,
        isError = isError,
        inputTransformation = inputTransformation,
        outputTransformation = outputTransformation,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = primaryLight,
                modifier = Modifier.size(dimensionResource(R.dimen.icon_default))
            )
        },
        label = {
            Text(
                text = stringResource(label),
                color = Color.Black,
            )
        },
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        lineLimits = TextFieldLineLimits.SingleLine,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primaryLight,
            unfocusedBorderColor = primaryLight,
            disabledBorderColor = Color.LightGray,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            errorBorderColor = errorLight
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.cabin_regular)),
            fontSize = with(LocalDensity.current) { dimensionResource(R.dimen.text_default).toSp() },
        ),
        modifier = if(readOnly){
            modifier
        }else {
            Modifier
                .padding(bottom = dimensionResource(R.dimen.padding_default))
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            // Scroll so this field is fully visible above keyboard
                            delay(250)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                }
        }
    )
}

