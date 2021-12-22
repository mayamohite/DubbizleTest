package com.example.dubizzletest.presentation.util

import android.content.Context
import android.widget.Toast

/**
 * Function to show toast
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
