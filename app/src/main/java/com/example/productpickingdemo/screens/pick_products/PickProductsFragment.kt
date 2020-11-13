package com.example.productpickingdemo.screens.pick_products

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.utils.injectViewModel

class PickProductsFragment : BaseFragment<PickProductsViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_pick_products
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): PickProductsViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

    }
}