package com.example.productpickingdemo.base.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.productpickingdemo.App
import com.example.productpickingdemo.database.AppDatabase
import com.example.productpickingdemo.database.shared_preferences.SharedPreferencesManager
import javax.inject.Inject

abstract class BaseViewModel(val app: Application) : AndroidViewModel(app) {
    @Inject
    protected lateinit var dbManager: AppDatabase

    @Inject
    protected lateinit var sharedPreferences: SharedPreferencesManager

    init {
        App[app].appComponent.inject(this)
    }
}