package com.example.dubizzletest.di

import com.example.dubizzletest.data.source.remote.RemoteProductDataSource
import com.example.dubizzletest.domain.ProductDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module to tell Hilt how to provide instances of types that cannot be constructor-injected.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(
        remoteDataSource: RemoteProductDataSource
    ): ProductDataSource
}
