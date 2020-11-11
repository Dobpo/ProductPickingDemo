package com.example.productpickingdemo.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.App
import com.example.productpickingdemo.R
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.dagger.components.FragmentComponent
import com.example.productpickingdemo.dagger.modules.FragmentModule
import javax.inject.Inject

abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity() {
    lateinit var viewModel: V

//    lateinit var fragmentComponent: FragmentComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    abstract fun layout(): Int

    abstract fun initialization()
    abstract fun provideViewModel(viewModelFactory: ViewModelProvider.Factory): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        initFragmentComponent()

        viewModel = provideViewModel(viewModelFactory)

        if (layout() != 0) {
            setContentView(layout())
            initialization()
        }
    }

//    fun initFragmentComponent() {
//        if (!::fragmentComponent.isInitialized) {
//            fragmentComponent = App[this].appComponent.plus(
//                FragmentModule(R.id.fragment_container, this as BaseActivity<BaseViewModel>)
//            )
//            fragmentComponent.inject(this)
//        }
//    }
}