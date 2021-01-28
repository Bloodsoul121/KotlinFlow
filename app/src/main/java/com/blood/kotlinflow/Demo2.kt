package com.blood.kotlinflow

import hu.akarnokd.kotlin.flow.onBackpressurureDrop
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {

        launch {
            for (j in 1..5) {
                delay(100)
                println("launch ${Thread.currentThread().name} $j")
            }
        }

        flow {
            for (i in 1..5) {
                delay(100)
                emit(i)
            }
        }
            .onBackpressurureDrop() // 扩展库
            .onCompletion { println("done") }
            .collect { println("${Thread.currentThread().name} >>> $it") }

    }

}