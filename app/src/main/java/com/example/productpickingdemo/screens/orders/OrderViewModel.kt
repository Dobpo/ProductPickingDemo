package com.example.productpickingdemo.screens.orders

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.Order
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderViewModel @Inject constructor(app: Application) : BaseViewModel(app) {

    fun getOrders(): LiveData<List<Order>> {
        val ordersLiveData: MutableLiveData<List<Order>> = MutableLiveData()
        viewModelScope.launch {
            val orders = dbManager.getOrders()

            withContext(Main) {
                ordersLiveData.postValue(orders)
            }
        }

        return ordersLiveData
    }
}