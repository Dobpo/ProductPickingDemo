package com.example.productpickingdemo.database

import com.example.productpickingdemo.database.entities.Location
import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product
import com.example.productpickingdemo.database.entities.User

interface AppDatabase {
    suspend fun initDatabase()
    suspend fun getOrders(): List<Order>
    suspend fun getProducts(orderId: Int): List<Product>
    suspend fun getLocation(id: Int): Location
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun deleteOrder(order: Order)
    suspend fun getUser(id: Int): User
}