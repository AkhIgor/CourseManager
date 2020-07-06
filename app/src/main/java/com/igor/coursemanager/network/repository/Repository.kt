package com.igor.coursemanager.network.repository

import com.igor.coursemanager.model.product.Currencies

interface Repository {

    suspend fun getCurrencies(date: String): Currencies
}