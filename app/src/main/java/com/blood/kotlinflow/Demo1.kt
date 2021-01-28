package com.blood.kotlinflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.system.measureTimeMillis

suspend fun main() {

    flow<Int> {
        for (i in 1..5) {
            delay(100)
            emit(i)
        }
    }.collect {
        println("${System.currentTimeMillis()} >>> $it")
    }

    println(Thread.currentThread().name)

    flowOf(1, 2, "a")
        .collect {
            println(">>> $it")
        }

    listOf(4, 5)
        .asFlow()
        .onEach {
            delay(100)
        }
        .collect {
            println(">>> $it")
        }

    runBlocking {
        val measureTimeMillis = measureTimeMillis {
            flow {
                for (i in 1..5) {
                    delay(100)
                    emit(i)
                }
            }.collect {
                delay(100)
            }
        }
        println(">>> flow measureTimeMillis $measureTimeMillis")
    }

    runBlocking {
        val measureTimeMillis = measureTimeMillis {
            channelFlow {
                for (i in 1..5) {
                    delay(100)
                    send(i)
                }
            }.collect {
                delay(100)
            }
        }
        println(">>> channelFlow measureTimeMillis $measureTimeMillis")
    }

    runBlocking {
        flow {
            for (i in 1..3) {
                println(">>> ${Thread.currentThread().name} emit")
                emit(i)
            }
        }
            .flowOn(Dispatchers.IO)
            .collect {
                println(">>> ${Thread.currentThread().name} $it")
            }
    }

    withTimeoutOrNull(2500) {
        flow {
            for (i in 1..3) {
                delay(1000)
                emit(i)
            }
        }
            .flowOn(Dispatchers.IO)
            .collect {
                println(">>> ${Thread.currentThread().name} $it")
            }
    }


}