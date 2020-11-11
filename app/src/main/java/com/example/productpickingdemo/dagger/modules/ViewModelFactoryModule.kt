package com.example.productpickingdemo.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.base.view_model.DaggerViewModelFactory
import com.example.productpickingdemo.dagger.scopes.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
//    @Binds
//    @FragmentScope
//    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}