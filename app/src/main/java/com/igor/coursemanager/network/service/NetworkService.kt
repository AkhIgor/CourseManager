package com.igor.coursemanager.network.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object NetworkService {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun centralBankApi() =
        retrofit.create(CentralBankApi::class.java)
}

private const val BASE_URL = "https://www.cbr.ru/scripts/"

private const val TEST_URL = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=06/07/2020"