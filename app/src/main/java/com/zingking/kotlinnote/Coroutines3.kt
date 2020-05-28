package com.zingking.kotlinnote

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

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

