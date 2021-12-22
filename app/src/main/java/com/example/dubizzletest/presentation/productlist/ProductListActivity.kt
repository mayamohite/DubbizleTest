package com.example.dubizzletest.presentation.productlist

import android.os.Bundle
import com.example.dubizzletest.R
import com.example.dubizzletest.base.BaseActivity
import com.example.dubizzletest.presentation.util.ImageCache
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductListActivity : BaseActivity() {

    @Inject
    lateinit var imageCache: ImageCache

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchProductListFragment()
        imageCache.initializeCache()
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
