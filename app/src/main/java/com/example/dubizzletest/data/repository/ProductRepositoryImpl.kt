package com.example.dubizzletest.data.repository

import com.example.dubizzletest.domain.ProductDataSource
import com.example.dubizzletest.domain.ProductRepository
import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.entities.Product
import com.example.dubizzletest.presentation.util.wrapEspressoIdlingResource
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource,
) : ProductRepository {

    override suspend fun getProductList(): Result<List<Product>> {
        wrapEspressoIdlingResource {
            return productDataSource.getProductList()
        }
    }
}
