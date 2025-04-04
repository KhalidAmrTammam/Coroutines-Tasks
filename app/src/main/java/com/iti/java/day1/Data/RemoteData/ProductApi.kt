package com.iti.java.day1.Data.RemoteData

import com.iti.java.day1.Data.Product
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}

data class ProductResponse(val products: List<Product>)
