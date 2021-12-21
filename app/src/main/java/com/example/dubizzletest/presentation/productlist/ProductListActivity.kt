package com.example.dubizzletest.presentation.productlist

import android.os.Bundle
import com.example.dubizzletest.R
import com.example.dubizzletest.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchProductListFragment()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private fun launchProductListFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container, ProductListFragment.newInstance()
        ).commit()
    }
}
