package com.example.dubizzletest.data.utils

import com.example.dubizzletest.data.source.remote.model.ProductDetail
import com.example.dubizzletest.data.source.remote.model.ProductResponse
import com.example.dubizzletest.domain.entities.Product

val EMPTY_PRODUCT_RESPONSE = ProductResponse(null)

val PRODUCT_RESPONSE = ProductResponse(
    results = listOf(
        ProductDetail(
            createdAt = "2019-02-24 04:53:52.285705",
            price = "AED 100",
            name = "hat",
            uid = "8e72663c6057482386f318b0ff2e8be2",
            imageIds = listOf("9662015c1f1c4226ad026ddc3390213d"),
            imageUrls = listOf("https://demo-app-photos-45687895456123.s3.amazonaws"),
            thumbnails = listOf(
                "https://demo-app-photos-45687895456123.s3.amazonaws"
            )
        ),
        ProductDetail(
            createdAt = "2019-02-24 04:53:52.285705",
            price = "AED 100000",
            name = "Car",
            uid = "8e72663c6057482386f318b0ff2e8be2",
            imageIds = listOf("9662015c1f1c4226ad026ddc3390213d"),
            imageUrls = listOf("https://demo-app-photos-45687895456123.s3.amazonaws"),
            thumbnails = listOf(
                "https://demo-app-photos-45687895456123.s3.amazonaws"
            )
        ),
    )
)

val PRODUCT_ENTITY = listOf(
    Product(
        name = "hat",
        price = "AED 100",
        images = listOf("https://demo-app-photos-45687895456123.s3.amazonaws"),
    ),
    Product(
        name = "Car",
        price = "AED 100000",
        images = listOf("https://demo-app-photos-45687895456123.s3.amazonaws"),
    ),
)

val IMAGE_URL = "https://picsum.photos/200/300"
