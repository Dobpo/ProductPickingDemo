package com.example.productpickingdemo.database.daos

import androidx.room.*

import com.example.productpickingdemo.database.entities.Location

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations")
    suspend fun getAll(): List<Location>

    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun getById(id: Long): Location

    @Insert
    suspend fun insert(location: Location)

    @Insert
    suspend fun insertAll(locations: List<Location>)

    @Update
    suspend fun update(location: Location)

    @Delete
    suspend fun delete(location: Location)
}