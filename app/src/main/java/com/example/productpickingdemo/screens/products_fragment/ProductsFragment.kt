package com.example.productpickingdemo.screens.products_fragment

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.data_base.entities.Order
import com.example.productpickingdemo.data_base.entities.Product
import com.example.productpickingdemo.screens.orders_fragment.OrderViewModel
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
        ordersList.add(Product(1,"test1","test1",1,1))
        ordersList.add(Product(1,"test2","test1",1,1))
        ordersList.add(Product(1,"test3","test1",1,1))
        ordersList.add(Product(1,"test4","test1",1,1))
        ordersList.add(Product(1,"test5","test1",1,1))
        ordersList.add(Product(1,"test6","test1",1,1))
        ordersList.add(Product(1,"test7","test1",1,1))
        ordersList.add(Product(1,"test8","test1",1,1))
        ordersList.add(Product(1,"test9","test1",1,1))
        ordersList.add(Product(1,"test10","test1",1,1))
        ordersList.add(Product(1,"test11","test1",1,1))
        ordersList.add(Product(1,"test12","test1",1,1))
        ordersList.add(Product(1,"test13","test1",1,1))
        ordersList.add(Product(1,"test14","test1",1,1))
        ordersList.add(Product(1,"test15","test1",1,1))
        ordersList.add(Product(1,"test16","test1",1,1))
        ordersList.add(Product(1,"test17","test1",1,1))
        ordersList.add(Product(1,"test18","test1",1,1))
        ordersList.add(Product(1,"test19","test1",1,1))
        ordersList.add(Product(1,"test20","test1",1,1))
        ordersList.add(Product(1,"test21","test1",1,1))

        rvProducts.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductsAdapter(ordersList, onClick = {
                    Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()
            })
        }
    }
}