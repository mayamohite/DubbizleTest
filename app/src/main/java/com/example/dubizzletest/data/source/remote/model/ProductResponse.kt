package com.example.dubizzletest.data.source.remote.model

import com.google.gson.annotations.SerializedName

/**
 * ProductResponse is used to handle retrofit api response.
 */
data class ProductResponse(
    val results: List<Result>?,
)

data class Result(
    @SerializedName("created_at")
    val createdAt: String?,
    val price: String?,
    val name: String?,
    val uid: String?,
    @SerializedName("image_ids")
    val imageIds: List<String>?,
    @SerializedName("image_urls")
    val imageUrls: List<String>?,
    @SerializedName("image_urls_thumbnails")
    val thumbnails: List<String>?,
)
