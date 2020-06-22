package com.igor.coursemanager.network.interactor

import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.network.repository.Repository

class InteractorImpl : Interactor {

    private val repository = Repository

    override suspend fun getCurrencies(date: SimpleDate): List<Currency> {
        return repository.getCurrencies()
    }
}