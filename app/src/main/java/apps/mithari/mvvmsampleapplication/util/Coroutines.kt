package apps.mithari.mvvmsampleapplication.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    fun main(work: suspend (() -> Unit)) =
//        the main function takes a suspend function(with no parameter) as parameter
//        and return nothing
        CoroutineScope(Dispatchers.Main)
            .launch { work() }
//      we changed the scope of the coroutine to main thread and then perform the work
}