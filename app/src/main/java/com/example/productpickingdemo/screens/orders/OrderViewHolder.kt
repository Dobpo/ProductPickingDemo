package com.example.productpickingdemo.screens.orders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.item_order.view.*

class OrderViewHolder(view: View) : ViewHolder(view) {
    var tvOrderNumber: TextView? = null
    var tvOrderInfo: TextView? = null

    init {
        tvOrderNumber = view.tvOrderNumber
        tvOrderInfo = view.tvOrderInfo
    }
}