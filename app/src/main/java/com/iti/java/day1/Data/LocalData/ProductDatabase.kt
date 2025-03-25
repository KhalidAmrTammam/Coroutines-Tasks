package com.iti.java.day1.Data.LocalData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iti.java.day1.Data.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile private var INSTANCE: ProductDatabase? = null

        fun getInstance(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java, "product_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
