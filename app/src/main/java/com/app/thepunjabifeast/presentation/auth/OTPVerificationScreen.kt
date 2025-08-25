package com.app.thepunjabifeast.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thepunjabifeast.ui.theme.Shapes
import com.app.thepunjabifeast.ui.theme.secondaryContainerLight

@Composable
fun OTPVerificationScreen(modifier: Modifier = Modifier) {
    BasicTextField(
        state = rememberTextFieldState(),
        modifier = modifier
            .semantics {
                contentType = ContentType.SmsOtpCode
            }
        ,
        inputTransformation = InputTransformation.maxLength(6),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = {
          //  val otpCode = state.text.toString()

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(6) { index->
                  //  Digit(otpCode.getOrElse(index, ' '))
                }
            }
        },
    )
}

@Composable
fun Digit(
    number: Char,
    modifier: Modifier = Modifier
) {
    Box(
        Modifier
            .size(48.dp)
            .border(1.5.dp, Color.Gray, Shapes.medium)
            .background(secondaryContainerLight, Shapes.medium)
    ) {
        Text(
            text = number.toString(),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}