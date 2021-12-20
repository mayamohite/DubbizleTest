package com.example.dubizzletest.data.source.remote

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dubizzletest.data.mapper.ProductMapper
import com.example.dubizzletest.data.source.remote.service.ProductApi
import com.example.dubizzletest.data.util.ConnectionChecker
import com.example.dubizzletest.data.utils.PRODUCT_ENTITY
import com.example.dubizzletest.data.utils.PRODUCT_RESPONSE
import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.entities.Product
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
class RemoteProductDataSourceTest {

    @Mock
    private lateinit var gitRepositoryApi: ProductApi

    @Mock
    private lateinit var connectionChecker: ConnectionChecker

    @Mock
    private lateinit var productMapper: ProductMapper

    private lateinit var remoteDataSource: RemoteProductDataSource
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun createRemoteDataSource() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteProductDataSource(
            gitRepositoryApi,
            connectionChecker,
            productMapper,
            context
        )
    }

    @Test
    fun `test remote data source returns error when internet connection is not available`() =
        runBlocking {
            whenever(connectionChecker.isConnectedToNetwork()).thenReturn(false)

            val gitRepositoryList = remoteDataSource.getProductList()

            assert(gitRepositoryList is Result.Error)
        }

    @Test
    fun `test remote data source returns error on failure from api`() = runBlocking {
        whenever(connectionChecker.isConnectedToNetwork()).thenReturn(true)
        whenever(gitRepositoryApi.getProductList())
            .thenThrow(IllegalArgumentException())

        val gitRepositoryList = remoteDataSource.getProductList()

        assert(gitRepositoryList is Result.Error)
    }

    @Test
    fun `test remote data source returns success`() = runBlocking {
        whenever(connectionChecker.isConnectedToNetwork()).thenReturn(true)
        whenever(gitRepositoryApi.getProductList()).thenReturn(PRODUCT_RESPONSE)
        whenever(productMapper.map(any())).thenReturn(PRODUCT_ENTITY)

        val gitRepositoryList: Result<List<Product>> = remoteDataSource.getProductList()

        assert(gitRepositoryList is Result.Success)
        assert((gitRepositoryList as Result.Success).data.size == PRODUCT_RESPONSE.results!!.size)
    }

}
