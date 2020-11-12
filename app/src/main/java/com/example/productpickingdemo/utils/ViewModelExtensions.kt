package com.example.productpickingdemo.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> androidx.fragment.app.FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
    //return ViewModelProviders.of(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> androidx.fragment.app.Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
    //return ViewModelProviders.of(this, factory)[T::class.java]
}

