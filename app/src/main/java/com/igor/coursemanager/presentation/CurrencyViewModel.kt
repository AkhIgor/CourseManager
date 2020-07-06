package com.igor.coursemanager.presentation

import androidx.lifecycle.*
import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.network.interactor.InteractorImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CurrencyViewModel(
    var date: SimpleDate
) : ViewModel(), LifecycleObserver {

    val progress = MutableLiveData<Boolean>()

    private val interactor = InteractorImpl()
    private val currencyEvent = MutableLiveData<List<Currency>>()

    fun getCurrencyEvent(): LiveData<List<Currency>> {
        return currencyEvent
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun getCurrencyList() {
        progress.value = true
        CoroutineScope(IO).launch {
            progress.postValue(true)
            currencyEvent.postValue(interactor.getCurrencies(date))
            progress.postValue(false)
        }
    }
}