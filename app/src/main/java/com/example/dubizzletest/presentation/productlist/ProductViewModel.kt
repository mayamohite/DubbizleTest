package com.example.dubizzletest.presentation.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dubizzletest.domain.common.Result
import com.example.dubizzletest.domain.entities.Product
import com.example.dubizzletest.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _productList = MutableLiveData<Result<List<Product>>>()

    val productList: LiveData<Result<List<Product>>> = _productList

    init {
        _productList.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val repositoryResult = getProductsUseCase.getProductList()
            _productList.postValue(repositoryResult)
        }
    }
}