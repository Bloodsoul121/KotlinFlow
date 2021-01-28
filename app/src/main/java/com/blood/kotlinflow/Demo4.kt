package com.blood.kotlinflow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {

        (1..3).asFlow()
            .transform {
                emit(it)
                emit("num $it")
            }
            .collect {
                println(">>> $it")
            }

        println("******************************************")

        (1..3).asFlow()
            .take(2)
            .collect {
                println(">>> $it")
            }

        val num = (1..5).asFlow()
            .reduce { accumulator, value ->
                println("reduce $accumulator + $value")
                accumulator + value
            }
        println(">>> num reduce $num")

        println("******************************************")

        val foldNum = (1..5).asFlow()
            .fold(0) { a: Int, b: Int ->
                println("fold $a + $b")
                a + b
            }
        println(">>> fold $foldNum")

        val foldNum2 = (1..5).asFlow()
            .fold(1) { a: Int, b: Int ->
                println("fold $a + $b")
                a + b
            }
        println(">>> fold $foldNum2")

        println("******************************************")


    }


}