package com.example.productpickingdemo.screens.products

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : BaseFragment<ProductsViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_products
    }

    private lateinit var adapter: ProductsAdapter
    private lateinit var order: Order

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): ProductsViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {
        order = arguments?.let { ProductsFragmentArgs.fromBundle(it).order }!!

        val title = "Product List in order ${order.number}"
        tvTitle.text = title

        adapter = ProductsAdapter(onClick = {
            navController.navigate(
                ProductsFragmentDirections.actionProductsFragmentToLocationFragment(order, it)
            )
        })

        rvProducts.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@ProductsFragment.adapter
        }

        viewModel.productsLiveData.observe(viewLifecycleOwner, {
            adapter.setItems(it)
        })

        viewModel.getProducts(order.id)
    }
}