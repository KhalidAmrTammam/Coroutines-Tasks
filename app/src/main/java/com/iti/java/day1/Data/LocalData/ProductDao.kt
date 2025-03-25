package com.iti.java.day1.Data.LocalData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iti.java.day1.Data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<Product>> // Change LiveData to Flow

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)
}