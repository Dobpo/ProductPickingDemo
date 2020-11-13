package com.example.productpickingdemo.screens.orders

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    val ordersLiveData: MutableLiveData<List<Order>> = MutableLiveData()

    fun getOrders() {
        CoroutineScope(IO).launch {
            val orders = dbManager.getOrders()

            withContext(Main) {
                ordersLiveData.postValue(orders)
            }
        }
    }
}