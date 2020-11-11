package com.example.productpickingdemo.dagger.components

import android.app.Application
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.dagger.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ViewModelFactoryModule::class,
        DataBaseModule::class,
        QrModule::class]
)
interface AppComponent {
//    fun plus(fragmentModule: FragmentModule): FragmentComponent

    fun inject(app: Application)
    fun inject(target: BaseViewModel)
}