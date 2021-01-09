package io.github.itsusinn.extension.thread

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.*

fun Thread(name:String,runnable: Runnable) = Thread(runnable,name)

open class SingleThread private constructor(
   val name: String
):CoroutineScope {

   constructor() : this("default")

   val executor: ExecutorService = Executors.newSingleThreadExecutor()
   override val coroutineContext = executor.asCoroutineDispatcher()

   fun shutdown() {
      executor.shutdown()
   }

   companion object Factory {
      fun create(name: String) = SingleThread(name)
   }
}