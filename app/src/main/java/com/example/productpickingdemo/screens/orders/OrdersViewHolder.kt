package com.example.productpickingdemo.screens.orders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.order_item.view.*

class OrdersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textView: TextView? = null

    init {
        textView = view.tvOrderId
    }
}