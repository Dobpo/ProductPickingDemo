package com.example.productpickingdemo.screens.products

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productpickingdemo.R
import com.example.productpickingdemo.database.entities.Product

class ProductsAdapter(
    private val onClick: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductsViewHolder>() {

    private var productsList: List<Product> = listOf()

    fun setItems(productsList: List<Product>) {
        this.productsList = productsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductsViewHolder(view).listen { pos, _ ->
            val item = productsList[pos]
            onClick(item)
        }
    }

    override fun getItemCount(): Int = productsList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.tvProductName?.text =
            "${productsList[position].name} - ${productsList[position].requestQuantity} pcs."
        holder.tvProductCode?.text = "Code: ${productsList[position].name}"
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}