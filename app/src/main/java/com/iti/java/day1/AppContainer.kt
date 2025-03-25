package com.iti.java.day1


import android.content.Context
import com.iti.java.day1.Data.LocalData.LocalDataSource
import com.iti.java.day1.Data.LocalData.ProductDao
import com.iti.java.day1.Data.LocalData.ProductDatabase
import com.iti.java.day1.Data.ProductRepository
import com.iti.java.day1.Data.RemoteData.RemoteDataSource
import com.iti.java.day1.Data.RemoteData.RetrofitHelper
import com.iti.java.day1.viewModels.ProductViewModelFactory

class AppContainer(context: Context) {

    private val database: ProductDatabase = ProductDatabase.getInstance(context)
    private val productDao: ProductDao = database.productDao()

    private val localDataSource = LocalDataSource(productDao)
    private val remoteDataSource = RemoteDataSource(RetrofitHelper.api)

    val productRepository = ProductRepository(remoteDataSource, localDataSource)

    val productViewModelFactory = ProductViewModelFactory(productRepository)
}
