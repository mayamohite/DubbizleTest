package com.example.dubizzletest.presentation.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL

const val TAG = "Image Download"

fun getImageBitmap(imageCache: ImageCache, imageUrl: String): Bitmap? {
    var image: Bitmap? = null
    var inSampleSize = 0
    if (imageCache.getImageFromCache(imageUrl) == null) {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        options.inSampleSize = inSampleSize
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            val stream = connection.inputStream
            options.inJustDecodeBounds = false
            image = BitmapFactory.decodeStream(stream, null, options)
            log(TAG, "Download success")
            return image
        } catch (e: Exception) {
            log(TAG, "Exception - $e")
        }
    }
    return image
}
