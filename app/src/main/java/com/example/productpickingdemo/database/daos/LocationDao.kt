package com.example.productpickingdemo.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.productpickingdemo.database.entities.Location

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations")
    fun getAll(): LiveData<List<Location>>

    @Query("SELECT * FROM locations WHERE id = :id")
    fun getById(id: Int): LiveData<Location>

    @Query("DELETE FROM locations")
    suspend fun clearData()

    @Insert
    suspend fun insert(location: Location)

    @Insert
    suspend fun insertAll(locations: List<Location>)

    @Update
    suspend fun update(location: Location)

    @Delete
    suspend fun delete(location: Location)
}