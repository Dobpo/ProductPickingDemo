package com.example.productpickingdemo.screens.orders

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.Order
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
        getOrders()

        ivResetDatabase.setOnLongClickListener {
            viewModel.initDatabase()
            Toast.makeText(context, "Database was reset", Toast.LENGTH_SHORT).show()
            getOrders()
            false
        }
    }

    private fun getOrders() {
        viewModel.getOrders().observe(viewLifecycleOwner) {
            showOrders(it)
        }
    }

    private fun showOrders(orders: List<Order>?) {
        if (orders == null || orders.isEmpty()) {
            tvNoOrders.visibility = View.VISIBLE
            rvOrders.visibility = View.GONE
        } else {
            tvNoOrders.visibility = View.GONE
            rvOrders.visibility = View.VISIBLE
            rvOrders.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = OrdersAdapter(orders, onClick = {
                    navController.navigate(
                        OrdersFragmentDirections.actionOrdersFragmentToProductsFragment(it)
                    )
                })
            }
        }
    }

    override fun onBackPressed(): Boolean {
        if (activity != null)
            activity?.finish()
        return true
    }
}