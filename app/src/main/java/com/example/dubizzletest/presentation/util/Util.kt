package com.example.dubizzletest.presentation.util

import android.content.Context
import android.os.Build
import android.widget.Toast
import com.example.dubizzletest.BuildConfig

const val PRODUCT = "product"

/**
 * Function to show toast
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Log messages in debug mode
 */
fun log(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        println("$tag : $message")
    }
}
