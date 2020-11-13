package com.example.productpickingdemo.screens.products

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    val productsLiveData: MutableLiveData<List<Product>> = MutableLiveData()

    fun getProducts(orderId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val products = dbManager.getProducts(orderId)

            withContext(Dispatchers.Main) {
                productsLiveData.postValue(products)
            }
        }
    }
}