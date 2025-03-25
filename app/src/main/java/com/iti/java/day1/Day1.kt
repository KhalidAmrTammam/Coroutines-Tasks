package com.iti.java.day1
/*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {

    val channel = Channel<Int>(capacity = 5)


    launch {
        for (i in 1..5) {
            println("Sending: $i")
            channel.send(i)
        }
        channel.close()
    }


    launch {
        for (received in channel) {
            println("Received: $received")
        }
    }


    val myFlow = flow {
        for (i in 1..5) {
            emit(i)
            delay(500)
        }
    }

    myFlow.collect { value ->
        println("Flow collected: $value")
    }
}*/

/*import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun generateEvenNumbers(): Flow<Int> = flow {
    var number = 0
    repeat(20) {
        emit(number)
        number += 2
        delay(1000)
    }
}

fun main() = runBlocking {
    generateEvenNumbers()
        .take(10)
        .collect { value ->
            println("Collected: $value")
        }
}*/


