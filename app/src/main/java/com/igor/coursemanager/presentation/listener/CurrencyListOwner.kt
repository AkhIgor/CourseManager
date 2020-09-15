package com.igor.coursemanager.presentation.listener

import com.igor.coursemanager.model.product.Currency

interface CurrencyListOwner {

    val currencyList: MutableList<Currency>

    fun updateList(filteredList: List<Currency>)
}