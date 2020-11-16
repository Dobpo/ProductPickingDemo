package com.example.productpickingdemo.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "client_id ") val clientId: Long?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "customer_name") val customerName: String?,
    @ColumnInfo(name = "order_id") val orderId: Long?
) : Parcelable