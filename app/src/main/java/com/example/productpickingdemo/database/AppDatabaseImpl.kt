package com.example.productpickingdemo.database

import com.example.productpickingdemo.database.daos.LocationDao
import com.example.productpickingdemo.database.daos.OrderDao
import com.example.productpickingdemo.database.daos.ProductDao
import com.example.productpickingdemo.database.daos.UserDao
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