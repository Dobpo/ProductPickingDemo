package com.example.productpickingdemo.dagger.modules

import android.app.Application
import com.example.productpickingdemo.data_base.AppDatabase
import com.example.productpickingdemo.data_base.DateBaseImpl
import com.example.productpickingdemo.qr.QrScannerManager
import com.example.productpickingdemo.qr.QrScannerManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val context: Application) {
    @Provides
    @Singleton
    fun provideRoomDataBase(): AppDatabase = DateBaseImpl(context)

    @Provides
    @Singleton
    fun provideQr(): QrScannerManager = QrScannerManagerImpl(context)
}