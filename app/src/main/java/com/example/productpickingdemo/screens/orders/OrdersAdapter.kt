package com.example.productpickingdemo.screens.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productpickingdemo.R
import com.example.productpickingdemo.database.entities.Order

class OrdersAdapter(
    private val ordersList: ArrayList<Order>,
    private val onClick: (order: Order) -> Unit
) :
    RecyclerView.Adapter<OrdersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item, parent, false)

        return OrdersViewHolder(view).listen { pos, _ ->
            val item = ordersList[pos]
            onClick(item)
        }
    }

    override fun getItemCount(): Int = ordersList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.textView?.text = "OrderID - ${ordersList[position].id}"
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}