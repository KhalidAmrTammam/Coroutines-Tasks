package com.iti.java.day1.Data

import androidx.lifecycle.LiveData
import com.iti.java.day1.Data.LocalData.LocalDataSource
import com.iti.java.day1.Data.RemoteData.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*class ProductRepository(serviceLocator: ServiceLocator) {
    private val remoteDataSource: RemoteDataSource=serviceLocator.getDependency(Dependency.REMOTE_SOURCE)
    private val localDataSource: LocalDataSource =serviceLocator.getDependency(Dependency.LOCAL_SOURCE)*/
class ProductRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) {
    val favoriteProducts: Flow<List<Product>> = localDataSource.getFavorites()

    fun fetchProducts(): Flow<List<Product>> = flow {
        emit(remoteDataSource.getProducts())
    }

    suspend fun addToFavorites(product: Product) {
        localDataSource.addFavorite(product)
    }

    suspend fun removeFromFavorites(product: Product) {
        localDataSource.removeFavorite(product)
    }
}
