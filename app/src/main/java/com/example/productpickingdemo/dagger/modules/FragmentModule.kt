package com.example.productpickingdemo.dagger.modules

import com.example.productpickingdemo.base.BaseActivity
import com.example.productpickingdemo.dagger.scopes.FragmentScope

import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val baseActivity: BaseActivity) {
    @Provides
    @FragmentScope
    fun provideBaseActivity(): BaseActivity = baseActivity
}