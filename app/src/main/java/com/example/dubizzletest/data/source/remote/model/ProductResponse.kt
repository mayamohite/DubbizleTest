package com.example.dubizzletest.data.source.remote.model

import com.squareup.moshi.Json

/**
 * ProductResponse is used to handle retrofit api response.
 */
data class ProductResponse(
    val results: List<ProductDetail>?,
)

data class ProductDetail(
    @field:Json(name = "created_at")
    val createdAt: String?,
    val price: String?,
    val name: String?,
    val uid: String?,
    @field:Json(name = "image_ids")
    val imageIds: List<String>?,
    @field:Json(name = "image_urls")
    val imageUrls: List<String>?,
    @field:Json(name = "image_urls_thumbnails")
    val thumbnails: List<String>?,
)
