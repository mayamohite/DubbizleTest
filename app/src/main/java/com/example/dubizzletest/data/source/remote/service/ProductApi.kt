package com.example.dubizzletest.data.source.remote.service

import com.example.dubizzletest.data.source.remote.model.ProductResponse
import retrofit2.http.GET

/**
 * Retrofit api calls
 */
interface ProductApi {

    @GET("/default/dynamodb-writer")
    suspend fun getProductList(): ProductResponse
}
