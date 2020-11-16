package com.example.productpickingdemo.screens.orders

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    fun getOrders(): LiveData<List<Order>> {
        return dbManager.getOrders()
    }

    fun initDatabase() = CoroutineScope(Dispatchers.IO).launch {
        dbManager.initDatabase()
    }
}