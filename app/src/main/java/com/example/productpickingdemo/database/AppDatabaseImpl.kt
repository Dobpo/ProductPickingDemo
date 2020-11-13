package com.example.productpickingdemo.database

import com.example.productpickingdemo.database.daos.LocationDao
import com.example.productpickingdemo.database.daos.OrderDao
import com.example.productpickingdemo.database.daos.ProductDao
import com.example.productpickingdemo.database.daos.UserDao
import com.example.productpickingdemo.database.entities.Location
import javax.inject.Inject

class AppDatabaseImpl @Inject constructor(
    private val locationDao: LocationDao,
    private val orderDao: OrderDao,
    private val userDao: UserDao,
    private val productDao: ProductDao
) : AppDatabase {

    override fun getLocation(locationId: Int): Location? {
        return locationDao.getById(locationId.toLong())
    }
}