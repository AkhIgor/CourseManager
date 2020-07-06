package com.igor.coursemanager.network.interactor

import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.network.repository.Repository
import com.igor.coursemanager.network.repository.RepositoryImpl

class InteractorImpl : Interactor {

    private val repository: Repository = RepositoryImpl

    override suspend fun getCurrencies(date: SimpleDate): List<Currency> {
        return repository.getCurrencies(date.toString()).currrencies
    }
}