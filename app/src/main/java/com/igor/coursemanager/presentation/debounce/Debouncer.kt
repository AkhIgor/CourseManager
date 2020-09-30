package com.igor.coursemanager.presentation.debounce

import kotlinx.coroutines.delay

class Debouncer(
    private val debounceTime: Long = 10_000L
) {
    var executing: Boolean = false

    suspend fun doAction(action: suspend () -> Unit) {
        executing = true
        val currTime = System.currentTimeMillis()
        println("currTime:$currTime ")
        println("delayed")
        delay(debounceTime)
        action()
        println("executed on: ${Thread.currentThread()}")
        executing = false
    }
}