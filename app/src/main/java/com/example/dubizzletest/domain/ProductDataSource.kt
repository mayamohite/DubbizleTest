package com.example.dubizzletest.domain

import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.entities.Product

interface ProductDataSource {

    suspend fun getProductList(): Result<List<Product>>
}
