package com.example.productpickingdemo.data_base

import android.app.Application

class DateBaseImpl(context: Application) : DateBase {
    override fun getProducts(): String {
        return "test products"
    }
}