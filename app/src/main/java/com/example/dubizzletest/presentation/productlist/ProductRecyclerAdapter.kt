package com.example.dubizzletest.presentation.productlist

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dubizzletest.R
import com.example.dubizzletest.domain.entities.Product
import com.example.dubizzletest.presentation.util.ImageCache
import javax.inject.Inject

class ProductRecyclerAdapter @Inject constructor(
) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {

    private var productList: List<Product>? = null

    @Inject
    lateinit var imageCache: ImageCache

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = layoutInflater.inflate(R.layout.row_product_detail, parent, false)
        return ProductViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList?.get(position)
        holder.tvProductName.text = product?.name
        holder.tvProductPrice.text = product?.price

        //From backend list of images got for product. In recycler view only one image is displayed.
        val bitmap: Bitmap? = imageCache.getImageFromCache(product?.images?.get(0))
        if (bitmap != null) {
            holder.ivProduct.setImageBitmap(bitmap)
        }
        holder.ivProduct
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    fun setProductList(productList: List<Product>?) {
        this.productList = productList
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProduct: ImageView = itemView.findViewById(R.id.iv_product)
        val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        val tvProductPrice: TextView = itemView.findViewById(R.id.tv_product_price)
    }
}
