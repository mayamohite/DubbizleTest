package com.example.dubizzletest.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String?,
    val price: String?,
    val images: List<String>?,
) : Parcelable
