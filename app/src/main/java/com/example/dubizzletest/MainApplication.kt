package com.example.dubizzletest

import android.app.Application
import com.example.dubizzletest.presentation.util.ImageCache
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * An application with @HiltAndroidApp that triggers Hilt's code generation and
 * adds an application-level dependency container.
 */
@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var imageCache: ImageCache

    override fun onCreate() {
        super.onCreate()
        imageCache.initializeCache()
    }
}
