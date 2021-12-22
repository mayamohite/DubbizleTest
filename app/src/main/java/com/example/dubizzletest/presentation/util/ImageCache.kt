package com.example.dubizzletest.presentation.util

import android.graphics.Bitmap
import android.util.LruCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageCache @Inject constructor() {

    private lateinit var memoryCache: LruCache<String, Bitmap>

    fun initializeCache() {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        log(this.javaClass.simpleName, "$cacheSize")
        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {

            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.byteCount / 1024
            }
        }
    }

    fun addImageToCache(key: String?, value: Bitmap?) {
        if (memoryCache.get(key) == null) {
            memoryCache.put(key, value)
        }
    }

    fun getImageFromCache(key: String?): Bitmap? {
        return if (key != null) {
            memoryCache.get(key)
        } else {
            null
        }
    }

    fun removeImageFromCache(key: String?) {
        memoryCache.remove(key)
    }

    fun clearCache() {
        memoryCache.evictAll()
    }
}
