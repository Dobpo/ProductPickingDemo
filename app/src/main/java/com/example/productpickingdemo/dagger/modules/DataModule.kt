package com.example.productpickingdemo.dagger.modules

import android.app.Application
import com.example.productpickingdemo.data_base.RoomDateBase
import com.example.productpickingdemo.data_base.RoomDateBaseImpl
import com.example.productpickingdemo.qr.QrScannerManager
import com.example.productpickingdemo.qr.QrScannerManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val context: Application) {
    @Provides
    @Singleton
    fun provideRoomDataBase(): RoomDateBase = RoomDateBaseImpl(context)

    @Provides
    @Singleton
    fun provideQr(): QrScannerManager = QrScannerManagerImpl(context)
}