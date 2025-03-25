package com.iti.java.day1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iti.java.day1.Data.Product
import com.iti.java.day1.Dependencies.MyApplication
import com.iti.java.day1.viewModels.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val serviceLocator = (application as MyApplication).serviceLocator
        val viewModelFactory = serviceLocator.getDependency<ProductViewModelFactory>(Dependency.VIEWMODEL_FACTORY)
        val viewModel: ProductViewModel by viewModels { viewModelFactory }*/
        val appContainer = (application as MyApplication).appContainer
        val viewModelFactory = appContainer.productViewModelFactory
        val viewModel: ProductViewModel by viewModels { viewModelFactory }

        setContent {
            val navController = rememberNavController()
            AppNavigation(navController, viewModel)
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, viewModel: ProductViewModel) {
    NavHost(navController, startDestination = "product_list") {
        composable("product_list") {
            ProductListScreen(viewModel, navController)
        }
        composable("favorites") {
            FavoriteListScreen(viewModel, navController)
        }
    }
}

@Composable
fun ProductListScreen(viewModel: ProductViewModel, navController: NavHostController) {
    val products by viewModel.allProducts.collectAsState()
    val favoriteProducts by viewModel.favoriteProducts.collectAsState()

    Column {
        Button(
            onClick = { viewModel.fetchProducts() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Fetch Products")
        }

        Button(
            onClick = { navController.navigate("favorites") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("View Favorites")
        }

        LazyColumn {
            items(products) { product ->
                ProductRow(
                    product = product,
                    isFavorite = favoriteProducts.any { it.id == product.id },
                    onFavoriteClick = { viewModel.toggleFavorite(product) }
                )
            }
        }
    }
}

@Composable
fun FavoriteListScreen(viewModel: ProductViewModel, navController: NavHostController) {
    val favoriteProducts by viewModel.favoriteProducts.collectAsState()
    Column {
        Button(
            onClick = { navController.navigate("product_list") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Back to Products")
        }

        LazyColumn {
            items(favoriteProducts) { product ->
                ProductRow(
                    product = product,
                    isFavorite = true,
                    onFavoriteClick = { viewModel.toggleFavorite(product) }
                )
            }
        }
    }
}
@Composable
fun ProductRow(product: Product, isFavorite: Boolean, onFavoriteClick: (Product) -> Unit) { // Fix: Allow passing Product
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(product.thumbnail),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Column(Modifier.weight(1f).padding(8.dp)) {
                Text(text = product.title, fontWeight = FontWeight.Bold)
                Text(text = "$${product.price}", color = Color.Gray)
            }
            IconButton(onClick = { onFavoriteClick(product) }) { // Now it correctly passes Product
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }
    }
}
