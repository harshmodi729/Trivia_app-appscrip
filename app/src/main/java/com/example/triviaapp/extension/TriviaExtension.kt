package com.example.triviaapp.extension

import android.content.Context
import android.widget.Toast

/**
 * extension method will check whether given [String] is empty and blank or not
 */
fun String.isEmptyOrBlank(): Boolean {
    return this.isEmpty() && this.isBlank()
}

/**
 * extension method will toast message on screen
 * @param message [String] will display in toast
 * @param length by default short, can be change when method is invoke
 */
fun Context.makeToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}