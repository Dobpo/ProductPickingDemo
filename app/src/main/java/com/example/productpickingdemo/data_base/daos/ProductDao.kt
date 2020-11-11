package com.example.productpickingdemo.data_base.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.productpickingdemo.data_base.entities.Product

interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<Product?>?

    @Query("SELECT * FROM products WHERE id = :id")
    fun getById(id: Long): Product?

    @Insert
    fun insert(product: Product?)

    @Insert
    fun insertAll(products: List<Product>?)

    @Update
    fun update(product: Product?)

    @Delete
    fun delete(product: Product?)
}