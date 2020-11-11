package com.example.productpickingdemo.data_base.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.productpickingdemo.data_base.entities.Order

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