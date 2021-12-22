package com.example.dubizzletest.di

import com.example.dubizzletest.data.repository.ProductRepositoryImpl
import com.example.dubizzletest.domain.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module to tell Hilt how to provide instances of types that cannot be constructor-injected.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(impl: ProductRepositoryImpl): ProductRepository
}
