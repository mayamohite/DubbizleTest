package com.example.dubizzletest.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dubizzletest.R
import com.example.dubizzletest.base.BaseFragment
import com.example.dubizzletest.domain.entities.Product
import com.example.dubizzletest.presentation.util.ResultObserver
import com.example.dubizzletest.presentation.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main UI for Product list.
 */
@AndroidEntryPoint
class ProductListFragment : BaseFragment() {

    private lateinit var fragmentView: View
    private val productViewModel: ProductViewModel by activityViewModels()

    private lateinit var progressBar: ProgressBar
//    private lateinit var productSelectionCallback: (product: Product) -> Unit

    @Inject
    lateinit var productListAdapter: ProductRecyclerAdapter
    lateinit var rvProduct: RecyclerView

    companion object {
        val TAG: String = ProductListFragment::class.java.name
        fun newInstance() = ProductListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_product_list, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.product_list)
        setupViews()
        observeDataChanges()
    }

    private fun setupViews() {
        rvProduct = fragmentView.findViewById(R.id.rv_product)
        rvProduct.adapter = productListAdapter
        rvProduct.layoutManager = GridLayoutManager(
            this.context,
            2
        )
        rvProduct.addItemDecoration(
            DividerItemDecoration(
                rvProduct.context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        rvProduct.addItemDecoration(
            DividerItemDecoration(
                rvProduct.context,
                DividerItemDecoration.VERTICAL
            )
        )
        productListAdapter.setProductSelectionCallback { product ->
            productViewModel.setSelectedProduct(product)
        }
        progressBar = fragmentView.findViewById(R.id.progress_bar)
    }

    private fun observeDataChanges() {
        productViewModel.productList.observe(
            viewLifecycleOwner,
            ResultObserver(
                hideLoading = ::hideLoading,
                showLoading = ::showLoading,
                onSuccess = ::updateProductList,
                onError = ::showErrorMessage
            )
        )
    }

    private fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    /**
     * Update recycler view
     */
    private fun updateProductList(productList: List<Product>) {
        productListAdapter.setProductList(productList)
    }

    /**
     * Show toast message if Exception is thrown.
     */
    private fun showErrorMessage(message: String) {
        this.activity?.toast(message)
    }

//    fun setProductSelectionCallback(onProductSelection: (product: Product) -> Unit) {
//        productSelectionCallback = onProductSelection
//    }
}
