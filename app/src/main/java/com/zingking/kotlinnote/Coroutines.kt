package com.zingking.kotlinnote

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

/**
 * Copyright © www.zingking.cn All Rights Reserved
 * author: Zkai
 * date: 2020/5/27
 * description: 协程：基础
 * https://www.kotlincn.net/docs/reference/coroutines/basics.html
 */

/**
 * 1. 第一个协程
 */
/*fun main() {
    // 全局作用域的协程
    GlobalScope.launch {
        delay(1000L) // 非阻塞
        printlnT("World!")
    }
    printlnT("Hello,") // 协程在delay时，主线程还在继续
    Thread.sleep(2000L) // 阻塞主线程 2000 ms 保证jvm存活
}*/

/**
 * 2.显示的调用阻塞 runBlocking
 */
/*fun main() {
    // 全局作用域的协程
    GlobalScope.launch {
        delay(1000L)
        printlnT("World!")
    }
    printlnT("Hello,")
    // 调用了 runBlocking 的主线程会一直阻塞到子协程执行完毕
    runBlocking {
        delay(2000L)
    }
}*/

// 使用 runBlocking 来包装 main 函数的执行：
/*fun main() = runBlocking<Unit>{
    GlobalScope.launch {
        delay(1000L)
        printlnT("World!")
    }
    printlnT("Hello,")
    delay(2000L) // 非阻塞 delay 变成了 阻塞的delay
}*/

/**
 * 3.显示(以非阻塞方式)等待所启动的后台job执行结束
 */
/*fun main() = runBlocking {
    val launch = GlobalScope.launch {
        delay(1000L)
        printlnT("World!")
    }
    printlnT("Hello,")
    launch.join() // suspend 方法 join 只能由协程或者另一个 suspend 调用
    printlnT("END?")
}*/

/**
 * 4.结构化的并发
 * 作用域
 */
/*fun main() = runBlocking {
    // this 当前协程对象

    // 启动一个新的协程
    launch {
        delay(1000L) // 外部协程(this)会在所有子协程执行完毕后才会结束
        printlnT("World!")
    }
    launch {
        delay(2000L) // 外部协程(this)会在所有子协程执行完毕后才会结束
        printlnT("END...")
    }
    printlnT("Hello,")
}*/

/**
 * 5.作用域构建器
 * coroutineScope 构建器可以声明自己的作用域，在所有已启动的字协程执行完毕之前不会结束，即所有子协程都执行完毕后才会结束
 */
/*fun main() = runBlocking {
    printlnT(this.toString())
    launch {
        delay(1000L)
        printlnT("TASK runBlocking")
    }
    // 新的协程作用域，相当于把里面的子协程捆绑到一起，都执行完毕，协程作用域才结束
    coroutineScope {
        printlnT(this.toString())
        launch {
            delay(1500L)
            printlnT("coroutineScop child coroutine")
        }
        delay(500L)
        printlnT("coroutineScop")
    }
    printlnT("END!")
}*/

/**
 * 6.提取(重构)函数
 * 就是把协程抽成方法，带有suspend的挂机函数
 */
/*fun main() = runBlocking {
    launch{
        doWorld()
    }
    printlnT("Hello,")
}
suspend fun doWorld(){
    delay(1000L)
    printlnT("World!")
}*/

/**
 * 7.一百万个协程
 */
/*fun main() = runBlocking {
    printlnT("START")
    repeat(1_000_000) {
        printlnT("repeat")
        val launch = launch {
            delay(1000L)
            printlnT("${this} . ${it}")
        }
//        launch.join()
    }
}*/

/*fun main(){
    val startTime = System.currentTimeMillis()
    repeat(1_000_000) {
        printlnT("repeat")
        thread {
            printlnT("${Thread.currentThread().name} . ${it}")
        }
    }
    val duration = System.currentTimeMillis() - startTime
    printlnT("$duration")
}
*/

