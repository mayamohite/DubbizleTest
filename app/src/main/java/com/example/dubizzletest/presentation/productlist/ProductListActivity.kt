package com.example.dubizzletest.presentation.productlist

import android.os.Bundle
import androidx.activity.viewModels
import com.example.dubizzletest.R
import com.example.dubizzletest.base.BaseActivity
import com.example.dubizzletest.domain.entities.Product
import com.example.dubizzletest.presentation.productdetails.ProductDetailsFragment
import com.example.dubizzletest.presentation.util.PRODUCT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : BaseActivity() {

    private var currentFrag = 0
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null || !savedInstanceState.containsKey("CURRENT_FRAG")) {
            launchProductListFragment()
        }

        productViewModel.selectedProduct.observe(this, {
            onProductSelection(it)
        })
    }

    override fun getLayout(): Int {
        return R.layout.activity_product
    }

    private fun launchProductListFragment() {
        currentFrag = 1
        val productListFragment = ProductListFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container, productListFragment
        ).addToBackStack(ProductListFragment.TAG).commit()
    }

    private fun onProductSelection(product: Product) {
        currentFrag = 2
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

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("CURRENT_FRAG", currentFrag)
        super.onSaveInstanceState(outState)
    }
}
