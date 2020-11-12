package com.example.productpickingdemo.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "row_id") val row: String?,
    @ColumnInfo(name = "row_barcode") val rowBarcode: String?,
    @ColumnInfo(name = "column_id") val column: String?,
    @ColumnInfo(name = "column_barcode") val columnBarcode: String?,
    @ColumnInfo(name = "shelf_id") val shelf: String?,
    @ColumnInfo(name = "shelf_barcode") val shelfBarcode: String?
)