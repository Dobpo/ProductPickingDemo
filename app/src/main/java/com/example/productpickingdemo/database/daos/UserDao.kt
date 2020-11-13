package com.example.productpickingdemo.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.productpickingdemo.database.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Int): LiveData<User>

    @Query("DELETE FROM users")
    suspend fun clearData()

    @Insert
    suspend fun insert(user: User)

    @Insert
    suspend fun insertAll(users: List<User>)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}