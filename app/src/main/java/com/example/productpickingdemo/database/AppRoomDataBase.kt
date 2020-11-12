package com.example.productpickingdemo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productpickingdemo.database.daos.LocationDao
import com.example.productpickingdemo.database.daos.OrderDao
import com.example.productpickingdemo.database.daos.ProductDao
import com.example.productpickingdemo.database.daos.UserDao
import com.example.productpickingdemo.database.entities.Location
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product
import com.example.productpickingdemo.database.entities.User

@Database(
    entities = [
        Location::class,
        Order::class,
        Product::class,
        User::class
    ],
    version = 1
)
abstract class AppRoomDataBase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun orderDao(): OrderDao
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
}