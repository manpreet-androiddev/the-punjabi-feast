package com.app.thepunjabifeast.presentation.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.thepunjabifeast.R
import com.app.thepunjabifeast.helpers.PhoneNumberOutputTransformation
import com.app.thepunjabifeast.data.model.ErrorMessage
import com.app.thepunjabifeast.ui.theme.ThePunjabiFeastTheme
import com.app.thepunjabifeast.presentation.components.AppButton
import com.app.thepunjabifeast.presentation.components.EditTextField
import com.app.thepunjabifeast.presentation.components.SecurePasswordEditField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    modifier: Modifier,
    showErrorSnackBar: (ErrorMessage) -> Unit,
    signupViewModel: SignupViewModel = hiltViewModel()
) {
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    val selectedDate = datePickerState.selectedDateMillis?.let {
        signupViewModel.convertMillisToDate(it)
    } ?: ""

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(
                    onClick = {
                      //  signupViewModel.dob.setTextAndPlaceCursorAtEnd(selectedDate)
                        showDatePicker = false
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDatePicker = false }
                ) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = false
            )
        }

      /*  DatePicker(
            state = datePickerState,
            title = {
                Text(text = "Select Date of Birth")
            },
            showModeToggle = false,
        )*/
    }
    Column(
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_20))
    ) {

        EditTextField(
            label = R.string.name,
            leadingIcon = Icons.Filled.AccountCircle,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            onKeyBoardDone = {},
            isError = !signupViewModel.isValidUsername,
            value = signupViewModel.userName,
            inputTransformation = {
                val filtered = asCharSequence()
                    .filter { it.isLetter() }
                    .take(22)

                if (filtered != asCharSequence().toString()) {
                    replace(0, length, filtered)
                }
            },
            readOnly = false,
            outputTransformation = OutputTransformation {}
        )

        EditTextField(
            label = R.string.email,
            leadingIcon = Icons.Filled.Email,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            onKeyBoardDone = {},
            isError = !signupViewModel.isValidEmail,
            value = signupViewModel.email,
            inputTransformation = InputTransformation,
            readOnly = false,
            outputTransformation = OutputTransformation {}
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true } // click works here
        ) {
            EditTextField(
                label = R.string.user_dob,
                leadingIcon = Icons.Filled.CalendarMonth,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                readOnly = true,
                onKeyBoardDone = {},
                inputTransformation = InputTransformation,
                isError = false,
                value = signupViewModel.dob,
                outputTransformation = OutputTransformation {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        showDatePicker = true
                    }
            )
        }

        EditTextField(
            label = R.string.user_phone,
            leadingIcon = Icons.Filled.Call,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            onKeyBoardDone = {},
            isError = !signupViewModel.isValidPhoneNumber,
            inputTransformation = InputTransformation.maxLength(9),
            value = signupViewModel.phoneNumber,
            readOnly = false,
            outputTransformation = PhoneNumberOutputTransformation()
        )

        SecurePasswordEditField(
            label = R.string.password,
            leadingIcon = Icons.Filled.Lock,
            isError = !signupViewModel.isValidPassword,
            state = signupViewModel.password,
            inputTransformation = { },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_default)))

        SecurePasswordEditField(
            label = R.string.confirm_password,
            leadingIcon = Icons.Filled.Lock,
            isError = !signupViewModel.isValidPassword,
            state = signupViewModel.confirmPassword,
            inputTransformation = { },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_default)))

        AppButton(
            R.string.register,
            onClick = { signupViewModel.firebaseSignUp(showErrorSnackBar)},
            isEnable = signupViewModel.isFormValid(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun SignupPreview() {
    ThePunjabiFeastTheme {
         SignupScreen(
             modifier = Modifier,
             showErrorSnackBar = {}
         )
    }
}