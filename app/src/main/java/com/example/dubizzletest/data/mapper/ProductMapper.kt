package com.example.dubizzletest.data.mapper

import com.example.dubizzletest.data.source.remote.model.ProductResponse
import com.example.dubizzletest.domain.common.DataToDomainMapper
import com.example.dubizzletest.domain.entities.Product
import javax.inject.Inject

/**
 * Mapper to convert @see[ProductResponse] model to domain layer model.
 */
class ProductMapper @Inject constructor(
) : DataToDomainMapper<ProductResponse, List<Product>>() {

    override fun map(input: ProductResponse): List<Product> {
        val productDetails: MutableList<Product> = mutableListOf()
        input.results.let {
            it?.forEach { productDetail ->
                val product = Product(
                    name = productDetail.name,
                    price = productDetail.price,
                    images = productDetail.imageUrls,
                )
                productDetails.add(product)
            }
        }
        return productDetails
    }
}

