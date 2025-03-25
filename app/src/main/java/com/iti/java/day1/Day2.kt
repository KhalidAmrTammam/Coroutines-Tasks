package com.iti.java.day1

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/*fun main() {
    val sharedFlow = MutableSharedFlow<String>(replay = 1)

    val scope = CoroutineScope(Dispatchers.Default)

    scope.launch {
        sharedFlow.collect { value ->
            println("Collector 1 received: $value")
        }
    }

    scope.launch {
        sharedFlow.collect { value ->
            println("Collector 2 received: $value")
        }
    }

    scope.launch {
        sharedFlow.emit("Hello")
        delay(500)
        sharedFlow.emit("World")
    }

    Thread.sleep(2000)
}*/
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/*
sealed class Response {
    data object Loading : Response()
    data class Success(val data: List<String>) : Response()
    data class Failure(val error: Throwable) : Response()
}

class MovieRepository {
    fun getAllMovies(): Flow<List<String>> = flow {
        emit(listOf("Movie 1", "Movie 2", "Movie 3"))
    }
}

class MovieViewModel(scope: CoroutineScope, private val repository: MovieRepository) {
    private val _movieList = MutableStateFlow<Response>(Response.Loading)
    val movieList: StateFlow<Response> = _movieList.asStateFlow()

    init {
        scope.launch {
            repository.getAllMovies()
                .catch { ex -> _movieList.value = Response.Failure(ex) }
                .collect { _movieList.value = Response.Success(it) }
        }
    }
}

fun main() {
    val scope = CoroutineScope(Dispatchers.Default)
    val viewModel = MovieViewModel(scope, MovieRepository())

    scope.launch {
        viewModel.movieList.collect { uiState ->
            when (uiState) {
                is Response.Loading -> println("Loading...")
                is Response.Success -> println("Movies: ${uiState.data}")
                is Response.Failure -> println("Error: ${uiState.error.message}")
            }
        }
    }

    Thread.sleep(3000)
}
*/
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NameSearch(scope: CoroutineScope) {
    private val _searchFlow = MutableSharedFlow<Char>(replay = 1)
    val searchFlow = _searchFlow.asSharedFlow()

    private val allNames = listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank")

    private val _filteredNames = MutableSharedFlow<List<String>>(replay = 1)
    val filteredNames = _filteredNames.asSharedFlow()

    init {
        scope.launch {
            searchFlow.collectLatest { query ->
                val results = allNames.filter { it.startsWith(query.toString(), ignoreCase = true) }
                _filteredNames.emit(results)
            }
        }
    }

    fun search(query: Char) {
        CoroutineScope(Dispatchers.IO).launch {
            _searchFlow.emit(query)
        }
    }
}

fun main() {
    val scope = CoroutineScope(Dispatchers.Default)
    val nameSearch = NameSearch(scope)

    scope.launch {
        nameSearch.filteredNames.collect { names ->
            println("Matching Names: $names")
        }
    }

    scope.launch {
        delay(500)
        nameSearch.search('a')
        delay(500)
        nameSearch.search('b')
        delay(500)
        nameSearch.search('c')
    }

    Thread.sleep(2000)
}


