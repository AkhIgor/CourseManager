package com.igor.coursemanager.network.repository

import com.igor.coursemanager.model.product.Currencies
import com.igor.coursemanager.network.service.CentralBankApi
import com.igor.coursemanager.network.service.NetworkService

object RepositoryImpl : Repository {

    private val centralBankApi: CentralBankApi = NetworkService.centralBankApi()

    override suspend fun getCurrencies(date: String): Currencies {
        return centralBankApi.getCurrencies(date)
    }
}