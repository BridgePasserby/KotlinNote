package com.zingking.kotlinnote

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Copyright © www.zingking.cn All Rights Reserved
 * author: Zkai
 * date: 2020/5/28
 * description: 协程：取消与超时
 * https://www.kotlincn.net/docs/reference/coroutines/cancellation-and-timeouts.html
 */

/**
 * 1.取消协程
 */
/*fun main() = runBlocking {
    val job = launch {
        repeat(1000) {
            delay(1000L)
            printlnT("sleep ${it}")
        }
    }
    delay(1000L)
    printlnT("I'm so tired.")
    job.cancel()
    job.join() // join 会等待协程结束才会执行后面的代码
    printlnT("END!")
}*/

/**
 * 2.取消是协作的
 * fixme 协作是什么意思？
 */
/*fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    // FIXME (Dispatchers.Default) 是什么意思
    val job = launch(Dispatchers.Default) {
        var nextTime = startTime
        var i = 0
        while (i <5) {
            if (System.currentTimeMillis() >= nextTime) {
                printlnT("sleep ${i++}")
                nextTime += 500L
            }
        }
    }
    delay(1300L)
    printlnT("I'm so tired")
    job.cancelAndJoin()
    printlnT("END!")
}*/

/**
 * 3.使计算代码可取消
 * 上面 2 的例子中，while 的代码块就是 计算代码
 * 使其可取消有 2 中方法：1.定期调用挂起函数(类似 1 中调用的 delay),这里可以使用yield
 * 2. 显示的检查取消状态
 *
 */
/*fun main() = runBlocking {
    // 定期调用挂起函数
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextTime = startTime
        var i = 0
        while (i < 5) {
            yield() // 和 delay(1L) 一样的效果，delay也是挂起函数
            if (System.currentTimeMillis() >= nextTime) {
                printlnT("sleep ${i++}")
                nextTime += 500L
            }
        }
    }
    delay(1300L)
    printlnT("I'm so tired")
    job.cancelAndJoin()
    printlnT("END!")
    // 显示的检查取消状态
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextTime = startTime
//        var i = 0
//        while (isActive) {
//            if (System.currentTimeMillis() >= nextTime) {
//                printlnT("sleep ${i++}")
//                nextTime += 500L
//            }
//        }
//    }
//    delay(1300L)
//    printlnT("I'm so tired")
//    job.cancelAndJoin()
//    printlnT("END!")
}*/

/**
 * 4.在finally中释放资源
 */
/*fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) {
                printlnT("repeat $it")
                delay(500L)
            }
        } finally {
            printlnT("finally")
        }
    }
    delay(1300L)
    printlnT("I'm tired")
    job.cancelAndJoin() // 等待所有终结动作执行完毕，这里指 finally ，所以可以在finally中释放资源
    printlnT("END!")
}*/

/**
 * 5.运行不能取消的代码块
 */
/*fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) {
                printlnT("repeat $it")
                delay(500L)
            }
        } finally {
            // 如果不声明以下代码不可取消，则不会打印 job: And I've...
            withContext(NonCancellable) {
                printlnT("finally")
                delay(1000L)
                printlnT("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L)
    printlnT("I'm tired")
    job.cancelAndJoin()
    printlnT("END!")
}*/

/**
 * 6.超时
 */
/*fun main() = runBlocking<Unit> {
    // withTimeout 会抛出 TimeoutCancellationException
//    try {
//        val result = withTimeout(1300L) {
//            repeat(1000) {
//                printlnT("repeat $it")
//                delay(500L)
//            }
//            1
//        }
//        printlnT("result = $result")
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
    // withTimeoutOrNull 通过返回为空代替抛出超时异常
    var result = withTimeoutOrNull(1300) {
        repeat(1000) {
            printlnT("repeat $it")
            delay(500L)
        }
        "Done"
    }
    printlnT("result = $result")
    printlnT("END!")
}*/
