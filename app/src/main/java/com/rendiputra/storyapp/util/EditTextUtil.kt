package com.rendiputra.storyapp.util

import android.text.InputType
import androidx.annotation.StringRes
import androidx.core.widget.doOnTextChanged
import com.rendiputra.storyapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.validateInput(@StringRes errorMessage: Int) {
    doOnTextChanged { _, _, _, count ->
        val grandParent = parent.parent
        if (grandParent is TextInputLayout) {
            if (count <= 0) {
                grandParent.error = context.getString(errorMessage)
                return@doOnTextChanged
            }
            grandParent.error = ""
            validateEmail(grandParent)
        }
    }
}

fun TextInputEditText.validateEmail(grandParent: TextInputLayout) {
    if ((inputType-1) != InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) return
    if (!isEmailFormatValid()) {
        grandParent.error = context.getString(R.string.email_not_valid)
    }
}

fun TextInputEditText.isNotEmpty(): Boolean {
    return text?.isNotEmpty() == true
}

fun TextInputEditText.isEmailFormatValid(): Boolean {
    return text.toString().validateEmail()
}

fun TextInputEditText.isLengthPasswordGreaterThan6(): Boolean {
    return text.toString().length >= 6
}