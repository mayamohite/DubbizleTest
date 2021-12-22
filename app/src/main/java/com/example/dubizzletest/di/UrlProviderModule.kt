package com.example.dubizzletest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module to provide base url.
 */
@Module
@InstallIn(SingletonComponent::class)
class UrlProviderModule {

    @Provides
    fun getBaseUrl(): String {
        return "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com"
    }
}
