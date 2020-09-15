package com.igor.coursemanager.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Int.convertNumber(): String {
    return if (this < 10) {
        "0$this"
    } else {
        toString()
    }
}

fun <T, R> T?.actionWithDebounce(debounceTime: Long = 10_000L, action: suspend () -> R) {
    this?.let {
        CoroutineScope(IO).launch {
            Debouncer.doAction(debounceTime) {
                action()
            }
        }
    }
}

object Debouncer {
    private var time: Long = System.currentTimeMillis()
    private var executing: Boolean = false


    suspend fun doAction(debounceTime: Long, action: suspend () -> Unit) {
        if (!executing) {
            executing = true
            val currTime = System.currentTimeMillis()
            println("currTime:$currTime ")
            if (currTime < debouncedTime(debounceTime)) {
                println("delayed")
                delay(debouncedTime(debounceTime) - currTime)
            } else {
                println("delayed")
                delay(debounceTime)
            }
            action()
            resetTime()
            println("executed on: ${Thread.currentThread()}")
            executing = false
        }
    }

    private fun debouncedTime(debounceTime: Long): Long {
        val time = (time + debounceTime)
        println("debouncedTime:$time ")
        return time
    }

    private fun resetTime() {
        time = System.currentTimeMillis()
    }
}