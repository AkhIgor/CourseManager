package com.igor.coursemanager.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.igor.coursemanager.R
import com.igor.coursemanager.databinding.CurrencyListDataBinding
import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.presentation.CurrencyViewModel
import com.igor.coursemanager.presentation.date.observer.DateObserver
import com.igor.coursemanager.presentation.date.owner.DateOwner
import com.igor.coursemanager.presentation.viewModelFactory
import com.igor.coursemanager.view.adapter.CurrencyListAdapter

class CurrencyFragment : Fragment(), DateObserver, LifecycleOwner {

    private var currencyList: MutableList<Currency> = mutableListOf()
    private val currencyAdapter = CurrencyListAdapter(currencyList)
    private lateinit var dateOwner: DateOwner

    private lateinit var currencyListView: RecyclerView
    private lateinit var viewModel: CurrencyViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        dateOwner = requireActivity() as DateOwner
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initDataBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
    }

    override fun updateObservedDate(newDate: SimpleDate) {
        viewModel.date = newDate
    }

    private fun initDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View? {
        val binding = DataBindingUtil.inflate<CurrencyListDataBinding>(
            inflater,
            R.layout.currency_fragment,
            container,
            false
        )

        binding.setVariable(com.igor.coursemanager.BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            viewModelFactory {
                CurrencyViewModel(
                    dateOwner.date
                )
            }
        ).get(CurrencyViewModel::class.java)
        lifecycle.addObserver(viewModel)
    }


    private fun initViews(view: View) {
        currencyListView = view.findViewById(R.id.currency_list_view)
        currencyListView.adapter = currencyAdapter

        viewModel.getCurrencyEvent().observeForever {
            currencyList.addAll(it)
            currencyAdapter.notifyDataSetChanged()
        }
    }
}