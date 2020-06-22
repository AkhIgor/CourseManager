package com.igor.coursemanager.presentation.date.observer

import com.igor.coursemanager.model.SimpleDate

interface DateObserver {

    fun updateObservedDate(newDate: SimpleDate)
}