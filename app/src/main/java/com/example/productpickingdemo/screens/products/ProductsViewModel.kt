package com.example.productpickingdemo.screens.products

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productpickingdemo.base.view_model.BaseViewModel
import com.example.productpickingdemo.database.entities.Product
import javax.inject.Inject

class ProductsViewModel @Inject constructor(app: Application) : BaseViewModel(app) {
    val productsLiveData: MutableLiveData<List<Product>> = MutableLiveData()

    fun getProducts(orderId: Int): LiveData<List<Product>> {
        return dbManager.getProducts(orderId)
    }
}