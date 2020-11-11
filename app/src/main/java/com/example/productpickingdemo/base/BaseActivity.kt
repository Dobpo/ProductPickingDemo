package com.example.productpickingdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.productpickingdemo.App
import com.example.productpickingdemo.dagger.components.FragmentComponent
import com.example.productpickingdemo.dagger.modules.FragmentModule
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    lateinit var fragmentComponent: FragmentComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragmentComponent()
    }

    fun initFragmentComponent() {
        if (!::fragmentComponent.isInitialized) {
            fragmentComponent = App[this].appComponent.plus(
                FragmentModule(this)
            )
            fragmentComponent.inject(this)
        }
    }
}