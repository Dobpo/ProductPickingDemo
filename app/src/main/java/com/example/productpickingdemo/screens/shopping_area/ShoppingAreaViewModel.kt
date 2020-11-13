package com.example.productpickingdemo.screens.shopping_area

import android.app.Application
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShoppingAreaViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    fun removeOrder(order: Order) {
        CoroutineScope(IO).launch {
            dbManager.deleteOrder(order)
        }
    }
}