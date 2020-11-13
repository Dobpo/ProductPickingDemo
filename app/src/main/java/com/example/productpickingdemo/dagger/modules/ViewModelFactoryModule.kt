package com.example.productpickingdemo.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.base.view_model.DaggerViewModelFactory
import com.example.productpickingdemo.dagger.ViewModelKey
import com.example.productpickingdemo.dagger.scopes.FragmentScope
import com.example.productpickingdemo.screens.location.LocationViewModel
import com.example.productpickingdemo.screens.login.LoginViewModel
import com.example.productpickingdemo.screens.orders.OrderViewModel
import com.example.productpickingdemo.screens.products.ProductsViewModel
import com.example.productpickingdemo.screens.shelf.ShelfViewModel
import com.example.productpickingdemo.screens.shopping_area.ShoppingAreaViewModel
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
    @ViewModelKey(LocationViewModel::class)
    internal abstract fun bindLocationViewModel(viewModel: LocationViewModel): ViewModel

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
    @ViewModelKey(ProductsViewModel::class)
    internal abstract fun bindProductsViewModel(viewModel: ProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(ShelfViewModel::class)
    internal abstract fun bindShelfViewModel(viewModel: ShelfViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(ShoppingAreaViewModel::class)
    internal abstract fun bindShoppingAreaViewModel(viewModel: ShoppingAreaViewModel): ViewModel
}