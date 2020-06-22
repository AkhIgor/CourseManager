package com.igor.coursemanager.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.igor.coursemanager.R
import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.network.interactor.Interactor
import com.igor.coursemanager.presentation.CurrencyViewModel
import com.igor.coursemanager.presentation.ViewModelFactory
import com.igor.coursemanager.presentation.date.observer.DateObserver
import com.igor.coursemanager.view.activity.MainActivity
import com.igor.coursemanager.view.adapter.CurrencyListAdapter

class CurrencyFragment : Fragment(R.layout.currency_fragment), DateObserver {

    private lateinit var currencyListView: RecyclerView
    private lateinit var currencyList: List<Currency>
    private val currencyAdapter = CurrencyListAdapter(currencyList)
    private lateinit var interactor: Interactor

    private lateinit var viewModel: CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, ViewModelFactory {
            CurrencyViewModel(
                interactor,
                (requireActivity() as MainActivity).date
            )
        })
            .get(CurrencyViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyListView = view.findViewById(R.id.currency_list_view)
        currencyListView.adapter = currencyAdapter
    }

    override fun updateObservedDate(newDate: SimpleDate) {
        viewModel.date = newDate
    }
}