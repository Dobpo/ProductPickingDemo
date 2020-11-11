package com.example.productpickingdemo.fragments

import android.os.Bundle
import android.view.View
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : BaseFragment() {
    override fun layout(): Int {
        return R.layout.fragment_orders
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoBack.setOnClickListener{
            navController.navigateUp()
        }
    }
}