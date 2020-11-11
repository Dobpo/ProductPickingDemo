package com.example.productpickingdemo.data_base.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.productpickingdemo.data_base.entities.Product
import com.example.productpickingdemo.data_base.entities.User

interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<Product?>?

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Long): User?

    @Insert
    fun insert(user: User?)

    @Insert
    fun insertAll(users: List<User>?)

    @Update
    fun update(user: User?)

    @Delete
    fun delete(user: User?)
}