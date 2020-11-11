package com.example.productpickingdemo.data_base.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "number") val number: Int?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "customer_name") val recipient: String?
)