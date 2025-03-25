package com.iti.java.day1.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iti.java.day1.Data.Product
import com.iti.java.day1.Data.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _favoriteProducts = MutableStateFlow<List<Product>>(emptyList())
    val favoriteProducts: StateFlow<List<Product>> = _favoriteProducts.asStateFlow()

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    val allProducts: StateFlow<List<Product>> = _allProducts.asStateFlow()

    init {
        viewModelScope.launch {
            repository.favoriteProducts.collect { _favoriteProducts.value = it }
        }
    }

    fun fetchProducts() {
        viewModelScope.launch {
            repository.fetchProducts().collect { _allProducts.value = it }
        }
    }

    fun addToFavorites(product: Product) {
        viewModelScope.launch {
            repository.addToFavorites(product)
        }
    }

    fun removeFromFavorites(product: Product) {
        viewModelScope.launch {
            repository.removeFromFavorites(product)
        }
    }
    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val currentFavorites = favoriteProducts.first()
            if (currentFavorites.any { it.id == product.id }) {
                repository.removeFromFavorites(product)
            } else {
                repository.addToFavorites(product)
            }
        }
    }


}
