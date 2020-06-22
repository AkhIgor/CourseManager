package com.igor.coursemanager.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.network.interactor.Interactor
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrencyViewModel(
    val interactor: Interactor,
    var date: SimpleDate
) : ViewModel() {

    val currencyEvent = MutableLiveData<List<Currency>>()
    val progress = MutableLiveData<Boolean>()

    private fun getCurrencyList() {
        progress.value = true
        GlobalScope.launch(IO) {
            currencyEvent.postValue(interactor.getCurrencies(date))
            progress.postValue(false)
        }
    }
}