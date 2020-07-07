package com.igor.coursemanager.presentation.date.owner

import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.presentation.date.observer.DateObserver

interface DateOwner {

    var date: SimpleDate

    fun addSubscriber(dateObserver: DateObserver)
}