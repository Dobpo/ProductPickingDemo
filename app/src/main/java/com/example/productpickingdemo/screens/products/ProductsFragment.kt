package com.example.productpickingdemo.screens.products

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product
import com.example.productpickingdemo.screens.orders.OrderViewModel
import com.example.productpickingdemo.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : BaseFragment<OrderViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_products
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): OrderViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

        val order: Order? = arguments?.let { ProductsFragmentArgs.fromBundle(it).order }

        tvOrder.text = "in order ${order?.id}"

        // TODO: 12.11.2020 for test
        val ordersList = arrayListOf<Product>()
        ordersList.add(Product(1, 1, 1, "test1", "test1", 1, 1))
        ordersList.add(Product(1, 1, 1, "test2", "test1", 1, 1))
        ordersList.add(Product(1, 1, 1, "test3", "test1", 1, 1))
        ordersList.add(Product(1, 1, 1, "test4", "test1", 1, 1))
        ordersList.add(Product(1, 2, 2, "test5", "test1", 1, 1))
        ordersList.add(Product(1, 2, 2, "test6", "test1", 1, 1))
        ordersList.add(Product(1, 2, 2, "test7", "test1", 1, 1))
        ordersList.add(Product(1, 2, 2, "test8", "test1", 1, 1))
        ordersList.add(Product(1, 2, 2, "test9", "test1", 1, 1))
        ordersList.add(Product(1, 2, 2, "test10", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test11", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test12", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test13", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test14", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test15", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test16", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test17", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test18", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test19", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test20", "test1", 1, 1))
        ordersList.add(Product(1, 3, 3, "test21", "test1", 1, 1))

        rvProducts.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductsAdapter(ordersList, onClick = {
                navController.navigate(
                    ProductsFragmentDirections.actionProductsFragmentToLocationFragment(order!!,it)
                )
            })
        }
    }
}