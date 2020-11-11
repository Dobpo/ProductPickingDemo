package com.example.productpickingdemo.data_base

import android.app.Application

class RoomDateBaseImpl(context: Application) : RoomDateBase {
    override fun getProducts(): String {
        return "test products"
    }
}