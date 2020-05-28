package com.zingking.kotlinnote

import java.text.SimpleDateFormat
import java.util.*

/**
 * Copyright © www.zingking.cn All Rights Reserved
 * author: Zkai
 * date: 2020/5/27
 * description: 工具类
 */

private val dataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.CHINA)

// 打印带时间的日志
fun printlnT(s: String) {
    println("${dataFormat.format(Date())}：${Thread.currentThread().name} - $s")
}