package com.example.productpickingdemo.database

import com.example.productpickingdemo.database.entities.Order
import com.example.productpickingdemo.database.entities.Product


interface AppDatabase {
    suspend fun initDatabase()
    suspend fun getOrders(): List<Order>
    suspend fun getProducts(orderId: Int): List<Product>
}