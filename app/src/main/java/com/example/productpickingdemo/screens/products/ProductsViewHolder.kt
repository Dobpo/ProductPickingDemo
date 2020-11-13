package com.example.productpickingdemo.screens.products

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textView: TextView? = null

    init {
        textView = view.tvProductName
    }
}