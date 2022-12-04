package com.rendiputra.storyapp.util

import android.util.Patterns

fun String.validateEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}