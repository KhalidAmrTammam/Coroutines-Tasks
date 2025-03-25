package com.iti.java.day1.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Product(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String?,
    val price: Double?,
    val discountPercentage: Double?,
    val rating: Double?,
    val stock: Int?,
    val brand: String?,
    val category: String?,
    val thumbnail: String?
)