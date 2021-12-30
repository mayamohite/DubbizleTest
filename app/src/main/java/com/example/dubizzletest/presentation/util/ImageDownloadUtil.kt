package com.example.dubizzletest.presentation.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

const val TAG = "Image Download"

class ImageDownloadUtil @Inject constructor(
    private val imageCache: ImageCache,
) {

    fun downloadImageBitmap(imageUrl: String) {
        wrapEspressoIdlingResource {
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
                    val image: Bitmap? = BitmapFactory.decodeStream(stream, null, options)
                    log(TAG, "Download success")
                    image?.let {
                        imageCache.addImageToCache(
                            imageUrl, image
                        )
                    }
                } catch (e: Exception) {
                    log(TAG, "Exception - $e")
                }
            }
        }
    }
}

