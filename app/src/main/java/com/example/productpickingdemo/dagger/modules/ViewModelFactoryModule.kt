package com.example.productpickingdemo.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.base.view_model.DaggerViewModelFactory
import com.example.productpickingdemo.dagger.ViewModelKey
import com.example.productpickingdemo.dagger.scopes.FragmentScope
import com.example.productpickingdemo.screens.confirm_unload.ConfirmUnloadViewModel
import com.example.productpickingdemo.screens.login_fragment.LoginViewModel
import com.example.productpickingdemo.screens.orders_fragment.OrderViewModel
import com.example.productpickingdemo.screens.scan_shopping_area.ScanShoppingAreaViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {
    @Binds
    @FragmentScope
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(OrderViewModel::class)
    internal abstract fun bindOrderViewModel(viewModel: OrderViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(ScanShoppingAreaViewModel::class)
    internal abstract fun bindScanShoppingAreaViewModel(viewModel: ScanShoppingAreaViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(ConfirmUnloadViewModel::class)
    internal abstract fun bindConfirmUnloadViewModel(viewModel: ConfirmUnloadViewModel): ViewModel
}