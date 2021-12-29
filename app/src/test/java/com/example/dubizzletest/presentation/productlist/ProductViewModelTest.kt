package com.example.dubizzletest.presentation.productlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dubizzletest.utils.PRODUCT_ENTITY
import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.usecases.GetProductsUseCase
import com.example.dubizzletest.presentation.util.CoroutinesDispatcherProvider
import com.example.dubizzletest.presentation.util.ImageDownloadUtil
import com.example.dubizzletest.utils.MainCoroutineRule
import com.example.dubizzletest.utils.runBlocking
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class ProductViewModelTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(testCoroutineDispatcher)

    @Mock
    lateinit var getProductsUseCase: GetProductsUseCase

    @Mock
    lateinit var imageDownloadUtil: ImageDownloadUtil

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadShot_emitsTwoUiModels() = mainCoroutineRule.runBlocking {
        //When
        whenever(getProductsUseCase.getProductList()).thenReturn(Result.Success(PRODUCT_ENTITY))
        //Pause dispatcher
        testCoroutineDispatcher.pauseDispatcher()

        val viewModel = ProductViewModel(
            getProductsUseCase,
            imageDownloadUtil,
            provideFakeCoroutinesDispatcherProvider(testCoroutineDispatcher)
        )
        //First live data update
        var result = viewModel.productList.getOrAwaitValue()
        assertTrue(result is Result.Loading)
        //Resume dispatcher
        testCoroutineDispatcher.resumeDispatcher()
        //Live data update with product list
        result = viewModel.productList.getOrAwaitValue()
        assertTrue(result is Result.Success)
    }


    @Test
    fun loadShot_emitsTwoUiModelsfailure() = testCoroutineDispatcher.runBlockingTest {
        //When
        whenever(getProductsUseCase.getProductList()).thenReturn(Result.Error(""))
        //Pause dispatcher
        testCoroutineDispatcher.pauseDispatcher()
        val viewModel = ProductViewModel(
            getProductsUseCase,
            imageDownloadUtil,
            provideFakeCoroutinesDispatcherProvider(testCoroutineDispatcher)
        )
        //First live data update
        var result = viewModel.productList.getOrAwaitValue()
        assertTrue(result is Result.Loading)
        //Resume dispatcher
        testCoroutineDispatcher.resumeDispatcher()
        //Live data update with error
        result = viewModel.productList.getOrAwaitValue()
        assertTrue(result is Result.Error)
    }

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    fun provideFakeCoroutinesDispatcherProvider(
        dispatcher: TestCoroutineDispatcher?
    ): CoroutinesDispatcherProvider {
        val sharedTestCoroutineDispatcher = TestCoroutineDispatcher()
        return CoroutinesDispatcherProvider(
            dispatcher ?: sharedTestCoroutineDispatcher,
            dispatcher ?: sharedTestCoroutineDispatcher,
            dispatcher ?: sharedTestCoroutineDispatcher
        )
    }
}
