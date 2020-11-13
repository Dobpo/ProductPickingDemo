package com.example.productpickingdemo.database

import androidx.lifecycle.LiveData
import com.example.productpickingdemo.database.entities.Location
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product
import com.example.productpickingdemo.database.entities.User

interface AppDatabase {
    suspend fun initDatabase()
    fun getOrders(): LiveData<List<Order>>
    fun getProducts(orderId: Int): LiveData<List<Product>>
    fun getLocation(id: Int): LiveData<Location>
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun deleteOrder(order: Order)
    fun getUser(id: Int): LiveData<User>
    fun getUsers(): LiveData<List<User>>
    suspend fun printDb()
}