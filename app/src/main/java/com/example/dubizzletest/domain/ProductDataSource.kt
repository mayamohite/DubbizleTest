package com.example.dubizzletest.domain

import com.example.dubizzletest.domain.entities.Product

interface ProductDataSource {

    suspend fun getProductList(): List<Product>
}
