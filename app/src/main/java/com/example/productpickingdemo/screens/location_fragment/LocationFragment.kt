package com.example.productpickingdemo.screens.location_fragment

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.screens.orders_fragment.OrderViewModel
import com.example.productpickingdemo.utils.injectViewModel

class LocationFragment : BaseFragment<LocationViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_location
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): LocationViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

    }

}