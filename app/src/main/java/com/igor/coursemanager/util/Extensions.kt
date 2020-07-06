package com.igor.coursemanager.util

fun Int.convertNumber(): String {
    return if (this < 10) {
        "0$this"
    } else {
        toString()
    }
}