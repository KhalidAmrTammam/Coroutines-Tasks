package com.iti.java.day1.Data.LocalData

import androidx.lifecycle.LiveData
import com.iti.java.day1.Data.Product
import kotlinx.coroutines.flow.Flow

/*class LocalDataSource(serviceLocator: ServiceLocator) {
    private val dao: ProductDao=serviceLocator.getDependency(Dependency.PRODUCT_DAO)*/
class LocalDataSource(private val dao: ProductDao) {

    fun getFavorites(): Flow<List<Product>> = dao.getAllFavorites()

    suspend fun addFavorite(product: Product) = dao.insertProduct(product)

    suspend fun removeFavorite(product: Product) = dao.deleteProduct(product)
}