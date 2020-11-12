package com.example.productpickingdemo.screens.orders_fragment

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.data_base.entities.Order
import com.example.productpickingdemo.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : BaseFragment<OrderViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_orders
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): OrderViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

        // TODO: 12.11.2020 for test
        val ordersList = arrayListOf<Order>()
        ordersList.add(Order(1, 232, "test", "test"))
        ordersList.add(Order(2, 2324, "test", "test"))
        ordersList.add(Order(3, 254, "test", "test"))
        ordersList.add(Order(4, 4562, "test", "test"))
        ordersList.add(Order(5, 2452, "test", "test"))
        ordersList.add(Order(6, 2, "test", "test"))
        ordersList.add(Order(7, 5462, "test", "test"))
        ordersList.add(Order(8, 2342, "test", "test"))
        ordersList.add(Order(9, 342, "test", "test"))
        ordersList.add(Order(10, 3242, "test", "test"))
        ordersList.add(Order(11, 2462, "test", "test"))
        ordersList.add(Order(12, 2, "test", "test"))
        ordersList.add(Order(13, 4672, "test", "test"))
        ordersList.add(Order(14, 9562, "test", "test"))
        ordersList.add(Order(15, 123142, "test", "test"))
        ordersList.add(Order(16, 2542, "test", "test"))
        ordersList.add(Order(17, 252, "test", "test"))
        ordersList.add(Order(18, 252, "test", "test"))
        ordersList.add(Order(19, 41342, "test", "test"))
        ordersList.add(Order(20, 3252, "test", "test"))
        ordersList.add(Order(21, 5145672, "test", "test"))
        ordersList.add(Order(22, 764932, "test", "test"))
        ordersList.add(Order(23, 73732, "test", "test"))
        ordersList.add(Order(24, 134152, "test", "test"))
        ordersList.add(Order(25, 154142, "test", "test"))
        ordersList.add(Order(26, 1613242, "test", "test"))
        ordersList.add(Order(27, 161462, "test", "test"))
        ordersList.add(Order(28, 9677442, "test", "test"))
        ordersList.add(Order(29, 562142, "test", "test"))
        ordersList.add(Order(30, 14512, "test", "test"))

        rvOrders.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = OrdersAdapter(ordersList, onClick = {
                navController.navigate(
                    OrdersFragmentDirections.actionOrdersFragmentToProductsFragment(
                        it
                    )
                )

            })
        }

    }
}