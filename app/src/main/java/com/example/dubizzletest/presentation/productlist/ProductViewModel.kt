package com.example.dubizzletest.presentation.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.entities.Product
import com.example.dubizzletest.domain.usecases.GetProductsUseCase
import com.example.dubizzletest.presentation.util.CoroutinesDispatcherProvider
import com.example.dubizzletest.presentation.util.ImageDownloadUtil
import com.example.dubizzletest.presentation.util.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val imageDownloadUtil: ImageDownloadUtil,
    private val dispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _productList = MutableLiveData<Result<List<Product>>>()
    val productList: LiveData<Result<List<Product>>> = _productList

    private val _selectedProduct = MutableLiveData<Product>()
    val selectedProduct: LiveData<Product> = _selectedProduct

    init {
        _productList.value = Result.Loading
        wrapEspressoIdlingResource {
            viewModelScope.launch(dispatcherProvider.io) {
                val productResult = getProductsUseCase.getProductList()
                cacheImages(productResult)
                _productList.postValue(productResult)
            }
        }
    }

    /**
     * Cache all product images in LruCache.
     */
    private suspend fun cacheImages(repositoryResult: Result<List<Product>>) {
        val imageDownloadJobs = ArrayList<Job>()
        if (repositoryResult is Result.Success) {
            supervisorScope {
                for (product in repositoryResult.data) {
                    if (product.images != null) {
                        for (imageUrl in product.images) {
                            wrapEspressoIdlingResource {
                                val imageDownloadJob = launch(dispatcherProvider.io) {
                                    imageDownloadUtil.downloadImageBitmap(imageUrl)
                                }
                                imageDownloadJobs.add(imageDownloadJob)
                            }
                        }
                    }
                }
            }
        }
        imageDownloadJobs.joinAll()
    }

    fun setSelectedProduct(product: Product) {
        _selectedProduct.value = product
    }
}
