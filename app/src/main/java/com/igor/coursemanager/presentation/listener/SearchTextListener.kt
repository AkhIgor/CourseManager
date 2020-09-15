package com.igor.coursemanager.presentation.listener

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.igor.coursemanager.util.actionWithDebounce
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchTextListener(
    private val listOwner: CurrencyListOwner
) : TextWatcher {

    private var observe = true

    override fun afterTextChanged(searchedText: Editable?) {
        if (observe && searchedText.toString().isNotBlank()) {
            actionWithDebounce {
                val searchedList = search(searchedText.toString().trim())
                withContext(Main) {
                    listOwner.updateList(searchedList)
                }
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    fun updateSearchedData(searchedText: Editable) {
        CoroutineScope(Main).launch {
            val searchedList = search(searchedText.toString().trim())
            listOwner.updateList(searchedList)
        }
    }

    fun clearSearch(observableView: EditText) {
        observe = false
        observableView.text.clear()
        observe = true
        listOwner.updateList(listOwner.currencyList)
    }

    private fun search(searchedValue: String?) =
        if (!searchedValue.isNullOrBlank()) {
            listOwner.currencyList.filter {
                it.name.contains(searchedValue, true)
                        || it.charCode.contains(searchedValue, true)
            }
        } else listOwner.currencyList
}

const val EMPTY_STRING = ""