package com.example.productpickingdemo.dagger.modules

import android.app.Application
import android.content.Context
import android.widget.Toast
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext
}