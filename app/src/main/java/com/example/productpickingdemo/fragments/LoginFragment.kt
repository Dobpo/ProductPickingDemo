package com.example.productpickingdemo.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    override fun layout(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToOrdersFragment())
        }
    }
}