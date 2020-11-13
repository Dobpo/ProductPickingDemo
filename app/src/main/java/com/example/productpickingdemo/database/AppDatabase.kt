package com.example.productpickingdemo.database

import com.example.productpickingdemo.database.entities.Location


interface AppDatabase {
    fun getProducts(): String
    fun getLocation(locationId: Int): Location?
}