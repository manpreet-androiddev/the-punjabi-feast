package com.app.thepunjabifeast.helpers

import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.insert

class PhoneNumberOutputTransformation: OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        if (length > 0) insert(0, "04")
        if (length > 4) insert(4, " ")
        if (length > 8) insert(8, " ")
    }
}