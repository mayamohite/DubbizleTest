package com.example.dubizzletest.data.repository

import com.example.dubizzletest.utils.PRODUCT_ENTITY
import com.example.dubizzletest.domain.ProductDataSource
import com.example.dubizzletest.domain.ProductRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import com.example.dubizzletest.domain.common.Result

class ProductRepositoryImplTest {

    lateinit var repository: ProductRepository

    @Mock
    lateinit var dataSource: ProductDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = ProductRepositoryImpl(dataSource)
    }

    @Test
    fun `test failure result when error get from data source`() = runBlocking {
        Mockito.`when`(dataSource.getProductList())
            .thenReturn(Result.Error("Products not available"))

        val repositoryDetails = repository.getProductList()

        assert(repositoryDetails is Result.Error)
    }

    @Test
    fun `test success result`() = runBlocking {
        Mockito.`when`(dataSource.getProductList())
            .thenReturn(Result.Success(PRODUCT_ENTITY))

        val repositoryDetails = repository.getProductList()

        assert(repositoryDetails is Result.Success)
        assertEquals(PRODUCT_ENTITY, (repositoryDetails as Result.Success).data)
    }
}
