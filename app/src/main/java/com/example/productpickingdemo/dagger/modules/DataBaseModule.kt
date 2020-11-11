package com.example.productpickingdemo.dagger.modules

import android.app.Application
import com.example.productpickingdemo.data_base.RoomDateBase
import com.example.productpickingdemo.data_base.RoomDateBaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(private val context: Application) {
    @Provides
    @Singleton
    fun provideRoomDataBase(): RoomDateBase = RoomDateBaseImpl(context)
}