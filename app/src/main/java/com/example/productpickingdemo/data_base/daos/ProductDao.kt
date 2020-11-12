package com.example.productpickingdemo.data_base.daos

import androidx.room.*
import com.example.productpickingdemo.data_base.entities.Product

@Dao
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