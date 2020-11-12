package com.example.productpickingdemo.data_base.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "products", foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("order_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Location::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("location_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "order_id") val orderId: Int?,
    @ColumnInfo(name = "location_id") val locationId: String?,
    @ColumnInfo(name = "name") val name: Int?,
    @ColumnInfo(name = "barcode") val barcode: String?,
    @ColumnInfo(name = "request_quantity") val requestQuantity: Int?,
    @ColumnInfo(name = "actual_quantity") val actualQuantity: Int?
)