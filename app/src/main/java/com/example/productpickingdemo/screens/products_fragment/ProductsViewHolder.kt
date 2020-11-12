package com.example.productpickingdemo.screens.products_fragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.order_item.view.*
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    var textView: TextView? = null

    init {
        textView= view.tvProductName
    }
}