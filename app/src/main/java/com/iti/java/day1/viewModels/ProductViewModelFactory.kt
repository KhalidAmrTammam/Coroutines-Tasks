package com.iti.java.day1.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iti.java.day1.Data.ProductRepository

/*class ProductViewModelFactory(serviceLocator: ServiceLocator) : ViewModelProvider.Factory {
    private val repository: ProductRepository=serviceLocator.getDependency(Dependency.REPOSITORY)*/
class ProductViewModelFactory(private val productRepository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
