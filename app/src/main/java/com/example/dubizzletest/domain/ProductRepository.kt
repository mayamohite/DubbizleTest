package com.example.dubizzletest.domain

import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.entities.Product

/**
 * Repository to get Product details.
 */
interface ProductRepository {

    suspend fun getProductList(): Result<List<Product>>
}
