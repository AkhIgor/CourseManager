package com.igor.coursemanager.network.interactor

import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.model.product.Currency

interface Interactor {

    suspend fun getCurrencies(date: SimpleDate) : List<Currency>
}