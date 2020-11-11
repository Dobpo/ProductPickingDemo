package com.example.productpickingdemo.dagger.modules

import android.app.Application
import com.example.productpickingdemo.qr.QrScannerManager
import com.example.productpickingdemo.qr.QrScannerManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class QrModule(private val context: Application) {
    @Provides
    @Singleton
    fun provideQr(): QrScannerManager = QrScannerManagerImpl(context)
}