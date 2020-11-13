package com.example.productpickingdemo.database

import com.example.productpickingdemo.database.entities.Location


interface AppDatabase {
    fun getLocation(locationId: Int): Location?
}