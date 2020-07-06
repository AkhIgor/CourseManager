package com.igor.coursemanager.network.service

import com.igor.coursemanager.model.product.Currencies
import retrofit2.http.POST
import retrofit2.http.Query

interface CentralBankApi {

    @POST("XML_daily.asp")
    suspend fun getCurrencies(@Query("date_req") date: String): Currencies
}