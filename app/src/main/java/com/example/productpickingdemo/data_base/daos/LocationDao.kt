package com.example.productpickingdemo.data_base.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import androidx.room.Update
import com.example.productpickingdemo.data_base.entities.Location


interface LocationDao {
    @Query("SELECT * FROM locations")
    fun getAll(): List<Location?>?

    @Query("SELECT * FROM locations WHERE id = :id")
    fun getById(id: Long): Location?

    @Insert
    fun insert(location: Location?)

    @Insert
    fun insertAll(locations: List<Location>?)

    @Update
    fun update(location: Location?)

    @Delete
    fun delete(location: Location?)
}