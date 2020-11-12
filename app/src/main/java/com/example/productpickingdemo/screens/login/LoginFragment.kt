package com.example.productpickingdemo.screens.login

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<LoginViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_login
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): LoginViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

        btnSubmit.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToOrdersFragment())
        }
    }
}