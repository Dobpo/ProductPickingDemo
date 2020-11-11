package com.example.productpickingdemo.base.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.productpickingdemo.App
import com.example.productpickingdemo.data_base.AppDatabase
import com.example.productpickingdemo.qr.QrScannerManager
import javax.inject.Inject

abstract class BaseViewModel(val app: Application) : AndroidViewModel(app) {
    @Inject
    lateinit var dbManager: AppDatabase

    @Inject
    lateinit var qrManager: QrScannerManager

    init {
        App[app].appComponent.inject(this)
    }
}