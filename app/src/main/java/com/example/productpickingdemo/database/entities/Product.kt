package com.example.productpickingdemo.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

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
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "order_id") val orderId: Int?,
    @ColumnInfo(name = "location_id") val locationId: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "barcode") val barcode: String?,
    @ColumnInfo(name = "request_quantity") val requestQuantity: Int?,
    @ColumnInfo(name = "actual_quantity") val actualQuantity: Int?
) : Parcelable