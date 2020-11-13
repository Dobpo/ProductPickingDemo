package com.example.productpickingdemo.screens.products

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productpickingdemo.R
import com.example.productpickingdemo.database.entities.Product

class ProductsAdapter(
    private val productsList: ArrayList<Product>,
    private val onClick: (product: Product) -> Unit
) :
    RecyclerView.Adapter<ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)

        return ProductsViewHolder(view).listen { pos, type ->
            val item = productsList[pos]
            onClick(item)
        }
    }

    override fun getItemCount(): Int = productsList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.textView?.text = "${productsList[position].name}"
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}