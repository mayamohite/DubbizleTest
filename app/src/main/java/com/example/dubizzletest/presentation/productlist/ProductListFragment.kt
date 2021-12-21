package com.example.dubizzletest.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dubizzletest.R
import com.example.dubizzletest.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main UI for Product list.
 */
@AndroidEntryPoint
class ProductListFragment : BaseFragment() {

    private lateinit var fragmentView: View

    companion object {
        fun newInstance() = ProductListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_product, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeDataChanges()
    }

    private fun setupViews() {

    }

    private fun observeDataChanges() {

    }
}
