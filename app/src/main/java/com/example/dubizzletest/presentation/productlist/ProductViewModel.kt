package com.example.dubizzletest.presentation.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.entities.Product
import com.example.dubizzletest.domain.usecases.GetProductsUseCase
import com.example.dubizzletest.presentation.util.ImageCache
import com.example.dubizzletest.presentation.util.getImageBitmap
import com.example.dubizzletest.presentation.util.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _productList = MutableLiveData<Result<List<Product>>>()
    val productList: LiveData<Result<List<Product>>> = _productList

    @Inject
    lateinit var imageCache: ImageCache

    init {
        _productList.value = Result.Loading
        wrapEspressoIdlingResource {
            viewModelScope.launch(Dispatchers.IO) {
                val repositoryResult = getProductsUseCase.getProductList()
                //Images are cached in LruCache.
                val imageDownloadJobs = ArrayList<Job>()
                if (repositoryResult is Result.Success) {
                    supervisorScope {
                        for (product in repositoryResult.data) {
                            if (product.images != null) {
                                for (imageUrl in product.images) {
                                    val job = launch(Dispatchers.IO) {
                                        imageCache.addImageToCache(
                                            imageUrl,
                                            getImageBitmap(imageCache, imageUrl)
                                        )
                                    }
                                    imageDownloadJobs.add(job)
                                }
                            }
                        }
                    }
                }
                _productList.postValue(repositoryResult)
            }
        }
    }
}
