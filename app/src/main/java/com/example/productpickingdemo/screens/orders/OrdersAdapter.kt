package com.example.productpickingdemo.screens.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productpickingdemo.R
import com.example.productpickingdemo.database.entities.Order

class OrdersAdapter(
    private val ordersList: List<Order>,
    private val onClick: (order: Order) -> Unit
) :
    RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)

        return OrderViewHolder(view).listen { pos, _ ->
            val item = ordersList[pos]
            onClick(item)
        }
    }

    override fun getItemCount(): Int = ordersList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.tvClientId?.text = "Client id : ${ordersList[position].clientId}"
        holder.tvOrderId?.text = "Order id : ${ordersList[position].orderId}"
        holder.tvCustomerName?.text = "${ordersList[position].customerName}"
        holder.tvDate?.text = "Date : ${ordersList[position].date}"
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}