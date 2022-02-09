package com.example.dubizzletest.presentation.productlist

import com.example.dubizzletest.R
import com.example.dubizzletest.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_product
    }
}
