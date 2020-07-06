package com.igor.coursemanager.model

import com.igor.coursemanager.util.convertNumber


data class SimpleDate(
    val day: Int,
    val month: Int,
    val year: Int
) {
    override fun toString(): String {
        return "${day.convertNumber()}/${month.convertNumber()}/$year"
    }
}