package com.example.dubizzletest.presentation.productlist

import android.os.Bundle
import com.example.dubizzletest.R
import com.example.dubizzletest.base.BaseActivity
import com.example.dubizzletest.domain.entities.Product
import com.example.dubizzletest.presentation.productdetails.ProductDetailsFragment
import com.example.dubizzletest.presentation.util.PRODUCT
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
        val productListFragment = ProductListFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container, productListFragment
        ).addToBackStack(ProductListFragment.TAG).commit()
        productListFragment.setProductSelectionCallback { product ->
            onProductSelection(product)
        }
    }

    private fun onProductSelection(product: Product) {
        val bundle = Bundle()
        bundle.putParcelable(PRODUCT, product)
        val productDetailsFragment = ProductDetailsFragment()
        productDetailsFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container, productDetailsFragment
        ).addToBackStack(productDetailsFragment.javaClass.simpleName).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
                .name.equals(ProductListFragment.TAG)
        ) {
            finish()
        }
        super.onBackPressed()
    }
}
