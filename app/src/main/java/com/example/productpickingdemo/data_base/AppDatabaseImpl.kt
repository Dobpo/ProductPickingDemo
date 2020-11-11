package com.example.productpickingdemo.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productpickingdemo.data_base.daos.LocationDao
import com.example.productpickingdemo.data_base.daos.OrderDao
import com.example.productpickingdemo.data_base.daos.ProductDao
import com.example.productpickingdemo.data_base.daos.UserDao
import com.example.productpickingdemo.data_base.entities.Location
import com.example.productpickingdemo.data_base.entities.Order
import com.example.productpickingdemo.data_base.entities.Product
import com.example.productpickingdemo.data_base.entities.User

@Database(
    entities = [
        Location::class,
        Order::class,
        Product::class,
        User::class
    ],
    version = 1
)
abstract class AppDatabaseImpl : RoomDatabase() {
    abstract fun locationDao(): LocationDao?
    abstract fun orderDao(): OrderDao?
    abstract fun productDao(): ProductDao?
    abstract fun userDao(): UserDao?
}