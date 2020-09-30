package com.igor.coursemanager.util

import com.igor.coursemanager.presentation.debounce.Debouncer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

fun Int.convertNumber(): String {
    return if (this < 10) {
        "0$this"
    } else {
        toString()
    }
}

fun <R> Debouncer?.actionWithDebounce(
    coroutineDispatcher: CoroutineDispatcher = IO,
    action: suspend () -> R
) {
    this?.let {
        if (!executing) {
            CoroutineScope(coroutineDispatcher).launch {
                doAction {
                    action()
                }
            }
        }
    }
}