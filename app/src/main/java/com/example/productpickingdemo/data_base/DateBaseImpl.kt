package com.example.productpickingdemo.data_base

import android.app.Application

class DateBaseImpl(context: Application) : AppDatabase {
    override fun getProducts(): String {
        return "test products"
    }
}