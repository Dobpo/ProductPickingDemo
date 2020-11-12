package com.example.productpickingdemo.database.daos

import androidx.room.*
import com.example.productpickingdemo.database.entities.Order

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    fun getAll(): List<Order?>?

    @Query("SELECT * FROM orders WHERE id = :id")
    fun getById(id: Long): Order?

    @Insert
    fun insert(order: Order?)

    @Insert
    fun insertAll(orders: List<Order>?)

    @Update
    fun update(order: Order?)

    @Delete
    fun delete(order: Order?)
}