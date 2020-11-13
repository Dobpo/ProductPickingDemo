package com.example.productpickingdemo.database.daos

import androidx.room.*
import com.example.productpickingdemo.database.entities.Order

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    suspend fun getAll(): List<Order>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getById(id: Long): Order

    @Query("DELETE FROM orders")
    suspend fun clearData()

    @Insert
    suspend fun insert(order: Order)

    @Insert
    suspend fun insertAll(orders: List<Order>)

    @Update
    suspend fun update(order: Order)

    @Delete
    suspend fun delete(order: Order)
}