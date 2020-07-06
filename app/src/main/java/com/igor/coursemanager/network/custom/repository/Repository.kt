package com.igor.coursemanager.network.custom.repository

import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.network.custom.converter.ResponseConverter
import com.igor.coursemanager.network.custom.model.Request

object Repository {
    private val converter =
        ResponseConverter()

    fun getCurrencies(): List<Currency> {
        val response = Request(
            SOME_PATH
        ).send()
        return converter.convert(response, Currency::class) ?: emptyList()
    }
}

private const val SOME_PATH = "some path"