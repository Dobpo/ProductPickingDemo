package com.example.productpickingdemo.screens.products

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.screens.shelf.ShelfFragmentDirections
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

        val title = "Product List in order ${order.orderId}"
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


        viewModel.getProducts(order.id).observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        btnFinish.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setMessage("Some Products still not Picked from Order ${order.id}. You want to finish?")
                .setNegativeButton("No") { dialog: DialogInterface, _: Int -> dialog.dismiss() }
                .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                    navController.navigate(
                        ProductsFragmentDirections.actionProductsFragmentToScanShoppingAreaFragment(
                            order
                        )
                    )
                }
                .show()
        }
    }
}