package com.example.productpickingdemo.database.daos

import androidx.room.*
import com.example.productpickingdemo.database.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User?>?

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