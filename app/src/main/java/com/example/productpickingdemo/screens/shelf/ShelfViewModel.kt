package com.example.productpickingdemo.screens.shelf

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShelfViewModel @Inject constructor(app: Application) : BaseViewModel(app) {

    fun getProducts(orderId: Int): LiveData<List<Product>> {
        return dbManager.getProducts(orderId)
    }

    fun deleteProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            dbManager.deleteProduct(product)
        }
    }

    fun getMode(): Int {
        return sharedPreferences.getMode()
    }

    fun setMode(mode: Int) {
        sharedPreferences.setMode(mode)
    }
}