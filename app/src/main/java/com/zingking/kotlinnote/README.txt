1.协程是什么
官方文档上说 “本质上，协程是轻量级的线程”。https://www.kotlincn.net/docs/reference/coroutines/basics.html
代码是运行在线程中，线程运行在进程中
协程是运行在线程中的。

协程运行在哪个线程需要现实的指明，例如：
        val launch = launch(Dispatchers.IO) {
            delay(1000L)
            println("${Thread.currentThread().name}")
            printlnT("${this} . ${it}")
        }
其中 Dispatchers.IO 表示协程运行在io线程

协程本来运行在主线程，当遇到suspend方法的时候，发生线程切换，根据 Dispatchers.IO 切换到 IO 线程；

个人理解：协程和线程在写法类似，都是要手动创建和手动回调(runOnUi withContext)；协程可以使用 job.join() 阻塞
            协程更轻量级，1_000_000 协程比同量级线程执行耗时更短