package com.example.productpickingdemo.database.daos

import androidx.room.*
import com.example.productpickingdemo.database.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getById(id: Long): User

    @Insert
    suspend fun insert(user: User)

    @Insert
    suspend fun insertAll(users: List<User>)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}