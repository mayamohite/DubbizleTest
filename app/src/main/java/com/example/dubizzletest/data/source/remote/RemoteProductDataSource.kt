package com.example.dubizzletest.data.source.remote

import com.example.dubizzletest.domain.ProductDataSource
import com.example.dubizzletest.domain.entities.Product

class RemoteProductDataSource : ProductDataSource {

    override suspend fun getProductList(): List<Product> {
        return emptyList()
    }
}
