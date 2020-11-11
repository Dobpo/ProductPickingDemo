package com.example.productpickingdemo.utils

import androidx.annotation.NonNull
import androidx.lifecycle.*

inline fun <reified T : ViewModel> androidx.fragment.app.FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> androidx.fragment.app.Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

