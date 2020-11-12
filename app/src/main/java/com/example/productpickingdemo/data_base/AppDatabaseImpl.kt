package com.example.productpickingdemo.data_base

import com.example.productpickingdemo.data_base.daos.LocationDao
import com.example.productpickingdemo.data_base.daos.OrderDao
import com.example.productpickingdemo.data_base.daos.ProductDao
import com.example.productpickingdemo.data_base.daos.UserDao
import javax.inject.Inject

class AppDatabaseImpl @Inject constructor(
    val locationDao: LocationDao,
    val orderDao: OrderDao,
    val userDao: UserDao,
    val productDao: ProductDao
) : AppDatabase {

    override fun getProducts(): String {
        return "test products"
    }
}