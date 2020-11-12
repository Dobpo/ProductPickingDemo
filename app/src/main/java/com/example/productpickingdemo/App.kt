package com.example.productpickingdemo

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.productpickingdemo.dagger.components.AppComponent
import com.example.productpickingdemo.dagger.components.DaggerAppComponent
import com.example.productpickingdemo.dagger.modules.AppModule
import com.example.productpickingdemo.dagger.modules.DatabaseModule
import com.example.productpickingdemo.dagger.modules.QrModule

class App : Application() {

    companion object {
        operator fun get(activity: Activity): App {
            return activity.application as App
        }

        operator fun get(context: Context): App {
            return context as App
        }
    }

    val appComponent: AppComponent by lazy {
        initDagger()
    }

    private fun initDagger(): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .qrModule(QrModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
}