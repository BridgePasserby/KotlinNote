package com.zingking.kotlinnote

import kotlinx.coroutines.*

/**
 * Copyright © www.zingking.cn All Rights Reserved
 * author: Zkai
 * date: 2020/5/28
 * description: 协程：组合挂起函数
 */

suspend fun doSomethingOne(): Int {
    delay(1000L) // 模拟耗时操作
    return 100
}

suspend fun doSomethingTwo(): Int {
    delay(1000L) // 模拟耗时操作
    return 10
}

/**
 * 1.默认顺序调用
 */
/*fun main() = runBlocking {
    val time = measureTimeMillis {
        var first = doSomethingOne()
        var second = doSomethingTwo()
        printlnT("The result is ${first + second}")
    }
    printlnT("Complete in $time ms ")
}*/

/**
 * 2.使用async并发
 */
/*fun main() = runBlocking {
    val time = measureTimeMillis {
        val first = async { doSomethingOne() }
        val second = async { doSomethingTwo() }
        printlnT("The answer is ${first.await() + second.await()}")
    }
    printlnT("Complete in $time ms")
}*/

/**
 * 3.惰性启动的async
 */
/*fun main() = runBlocking {
    val time = measureTimeMillis {
        val first = async(start = CoroutineStart.LAZY) { doSomethingOne() }
        val second = async(start = CoroutineStart.LAZY) { doSomethingTwo() }
        first.start()
        second.start()
        // 不调用start则按默认顺序执行
        printlnT("The answer is ${first.await() + second.await()}")
    }
    printlnT("Complete in $time ms")
}*/

/**
 * 4.使用async的结构并发
 */
/*fun main() = runBlocking<Unit> {
//    val time = measureTimeMillis {
//        printlnT("The answer is ${concurrentSum()}")
//    }
//    printlnT("Complete in $time ms")
    try {
        filedConcurrentSum()
    } catch (e: Exception) {
        printlnT("message = ${e.message}")
        printlnT("Computation failed with KotlinNullPointerException")
    }
}*/

suspend fun concurrentSum(): Int = coroutineScope {
    val first = async { doSomethingOne() }
    val second = async { doSomethingTwo() }
    first.await() + second.await()
}

suspend fun filedConcurrentSum(): Int = coroutineScope {
    val first = async<Int> {
        try {
            delay(Int.MAX_VALUE.toLong())
            100
        } finally {
            // second 抛出空指针，这里就会立即执行，则代表 delay 已经被取消
            printlnT("First child was cancelled")
        }
    }
    val second = async<Int> {
        printlnT("Throw an exception.")
        throw KotlinNullPointerException()
    }
    first.await() + second.await()
}
