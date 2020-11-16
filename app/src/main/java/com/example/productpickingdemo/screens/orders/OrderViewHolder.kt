package com.example.productpickingdemo.screens.orders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.item_order.view.*

class OrderViewHolder(view: View) : ViewHolder(view) {
    var tvClientId: TextView? = null
    var tvOrderId: TextView? = null
    var tvCustomerName: TextView? = null
    var tvDate: TextView? = null

    init {
        tvClientId = view.tvClientId
        tvOrderId = view.tvOrderId
        tvCustomerName = view.tvCustomerName
        tvDate = view.tvDate
    }
}