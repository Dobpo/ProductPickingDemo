package com.example.productpickingdemo.screens.shelf

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.utils.injectViewModel

class ShelfFragment : BaseFragment<ShelfViewModel>() {
    override fun layout(): Int {
        return R.layout.fragment_shelf
    }

    override fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): ShelfViewModel {
        return injectViewModel(viewModelFactory)
    }

    override fun initialization(view: View, isFirstInit: Boolean) {

    }
}