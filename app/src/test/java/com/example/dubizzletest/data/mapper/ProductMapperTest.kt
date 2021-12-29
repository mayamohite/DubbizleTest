package com.example.dubizzletest.data.mapper

import com.example.dubizzletest.data.source.remote.model.ProductResponse
import com.example.dubizzletest.utils.EMPTY_PRODUCT_RESPONSE
import com.example.dubizzletest.utils.PRODUCT_ENTITY
import com.example.dubizzletest.utils.PRODUCT_RESPONSE
import com.example.dubizzletest.utils.parametersOf
import com.example.dubizzletest.domain.entities.Product
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ProductMapperTest(
    private val response: ProductResponse,
    private val expectedResult: Any
) {

    private val mapper = ProductMapper()

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0} is mapped to {1}")
        fun testData() = parametersOf(
            EMPTY_PRODUCT_RESPONSE to emptyList<Product>(),
            PRODUCT_RESPONSE to PRODUCT_ENTITY,
        )
    }

    @Test
    fun `mapper produces expected result`() {
        Assert.assertEquals(expectedResult, mapper.map(response))
    }
}
