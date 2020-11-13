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
    @ColumnInfo(name = "number") val number: Long?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "customer_name") val recipient: String?
) : Parcelable