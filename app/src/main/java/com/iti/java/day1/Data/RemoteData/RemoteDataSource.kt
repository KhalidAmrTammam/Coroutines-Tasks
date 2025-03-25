package com.iti.java.day1.Data.RemoteData

import com.iti.java.day1.Data.Product

/*class RemoteDataSource(serviceLocator: ServiceLocator) {
    private val api: ProductApi=serviceLocator.getDependency(Dependency.PRODUCT_SERVICE)*/
class RemoteDataSource(private val api: ProductApi) {
    suspend fun getProducts(): List<Product> {
        return try {
            api.getProducts().products.map { product ->
                Product(
                    id = product.id,
                    title = product.title ?: "No Title",
                    description = product.description ?: "No Description",
                    price = product.price ?: 0.0,
                    discountPercentage = product.discountPercentage ?: 0.0,
                    rating = product.rating ?: 0.0,
                    stock = product.stock ?: 0,
                    brand = product.brand ?: "Unknown",
                    category = product.category ?: "Unknown",
                    thumbnail = product.thumbnail ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
