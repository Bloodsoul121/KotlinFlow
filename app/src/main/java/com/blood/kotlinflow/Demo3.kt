package com.blood.kotlinflow

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

fun main() {

    runBlocking {

        flow {
            emit(1)
            throw RuntimeException()
        }
            .onCompletion {
                if (it == null) {
                    println("done $it")
                } else {
                    println("done error $it")
                }
            }
            .catch { println("catch exception") }
            .collect {
                println(">>> $it")
            }

        println(">>>>>>>>>>>>>>>>>>>>>>>>>>")

        flow {
            emit(1)
            throw RuntimeException()
        }
            .catch { println("catch exception") }
            .onCompletion {
                if (it == null) {
                    println("done $it")
                } else {
                    println("done error $it")
                }
            }
            .collect {
                println(">>> $it")
            }

    }


}