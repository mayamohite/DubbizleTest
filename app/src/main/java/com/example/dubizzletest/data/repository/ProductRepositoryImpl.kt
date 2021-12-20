package com.example.dubizzletest.data.repository

import com.example.dubizzletest.domain.ProductDataSource
import com.example.dubizzletest.domain.ProductRepository
import com.example.dubizzletest.domain.entities.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource,
) : ProductRepository {

    override suspend fun getProductList(): List<Product> {
        return productDataSource.getProductList()
    }
}
