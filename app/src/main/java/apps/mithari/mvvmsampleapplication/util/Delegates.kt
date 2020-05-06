package apps.mithari.mvvmsampleapplication.util

import kotlinx.coroutines.*

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) { block.invoke(this) }
//        GlobalScope means it will end when application is exited. "this" used above
//        is for scope of the coroutine which is "GlobalScope" in our case
    }
}