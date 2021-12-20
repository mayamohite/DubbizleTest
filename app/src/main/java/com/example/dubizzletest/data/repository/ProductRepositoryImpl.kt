package com.example.dubizzletest.data.repository

import com.example.dubizzletest.domain.ProductRepository
import com.example.dubizzletest.domain.entities.Product

class ProductRepositoryImpl : ProductRepository {

    override suspend fun getProductList(): List<Product> {
        return emptyList()
    }
}
