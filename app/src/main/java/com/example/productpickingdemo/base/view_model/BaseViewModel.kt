package com.example.productpickingdemo.base.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.productpickingdemo.App
import com.example.productpickingdemo.database.AppDatabase
import javax.inject.Inject

abstract class BaseViewModel(val app: Application) : AndroidViewModel(app) {
    @Inject
    lateinit var dbManager: AppDatabase

    init {
        App[app].appComponent.inject(this)
    }
}