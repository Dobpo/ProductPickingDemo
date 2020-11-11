package com.example.productpickingdemo.dagger.components

import com.example.productpickingdemo.base.BaseActivity
import com.example.productpickingdemo.base.BaseFragment
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.dagger.scopes.FragmentScope
import com.example.productpickingdemo.dagger.modules.FragmentModule
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(target: BaseActivity<BaseViewModel>)
    fun inject(target: BaseFragment<BaseViewModel>)
}