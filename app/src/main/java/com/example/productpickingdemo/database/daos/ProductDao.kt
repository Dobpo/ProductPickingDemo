package com.example.productpickingdemo.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.productpickingdemo.database.entities.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    suspend fun getAll(): List<Product>

    @Query("SELECT * FROM products WHERE order_id = :order_id")
    fun getById(order_id: Int): LiveData<List<Product>>

    @Query("DELETE FROM products")
    suspend fun clearData()

    @Insert
    suspend fun insert(product: Product)

    @Insert
    suspend fun insertAll(products: List<Product>)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)
}