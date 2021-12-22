package com.example.dubizzletest.domain.usecases

import com.example.dubizzletest.domain.ProductRepository
import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.entities.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) : BaseUseCase() {

    suspend fun getProductList(): Result<List<Product>> {
        return productRepository.getProductList()
    }
}
