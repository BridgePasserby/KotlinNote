package com.zingking.kotlinnote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    var index = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.Main) {
            printlnT("GlobalScope")
            testThread()
        }
        tv_title.setOnClickListener {
            tv_desc.text = "${++index}"
        }
    }

    suspend fun testThread() {
        coroutineScope {
            printlnT("START")
            var result = 0
            val job = launch(Dispatchers.IO) {
                delay(3000L)
                result = 10
                printlnT("fetch data")
//                withContext(Dispatchers.Main) {
//                    tv_title.text = "￥$result"
//                }
            }
//            job.join()
            printlnT("END")
            tv_title.text = "￥$result"
        }
    }
}
