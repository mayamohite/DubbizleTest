package com.example.dubizzletest.data.source.remote

import android.content.Context
import com.example.dubizzletest.R
import com.example.dubizzletest.data.mapper.ProductMapper
import com.example.dubizzletest.data.source.remote.service.ProductApi
import com.example.dubizzletest.data.util.ConnectionChecker
import com.example.dubizzletest.domain.ProductDataSource
import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.entities.Product
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception
import javax.inject.Inject

class RemoteProductDataSource @Inject constructor(
    private val productApi: ProductApi,
    private val connectionChecker: ConnectionChecker,
    private val productMapper: ProductMapper,
    @ApplicationContext private val context: Context,
) : ProductDataSource {

    override suspend fun getProductList(): Result<List<Product>> {
        if (!connectionChecker.isConnectedToNetwork()) {
            return Result.Error(context.getString(R.string.internet_connection_error))
        }
        return try {
            val response = productApi.getProductList()
            Result.Success(productMapper.map(response))
        } catch (exception: Exception) {
            return Result.Error(exception.message ?: context.getString(R.string.unknown_error))
        }
    }
}
